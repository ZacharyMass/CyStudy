package com.jr7.cystudy.sockets;

import com.jr7.cystudy.model.Game;
import com.jr7.cystudy.model.Terms;
import com.jr7.cystudy.service.GameService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@ServerEndpoint("/websocket/{username}")
@Component
public class Server {

  // Store all socket session and their corresponding username.
  private static Map<Session, String> sessionUsernameMap = new HashMap<>();
  private static Map<String, Session> usernameSessionMap = new HashMap<>();

  private static Game g = new Game();
  @Autowired GameService gameService;

  private final Logger logger = LoggerFactory.getLogger(Server.class);

  /*TODO
   * - Check for same username entering game
   * - multiple threads for other groups of 2 playing
   */
  /**
   * What to do when the websocket is opened.
   *
   * @param session session info
   * @param username username of the user joining the session
   */
  @OnOpen
  public void onOpen(Session session, @PathParam("username") String username) throws IOException {

    // get session and websocket connection
    logger.info("Entered onOpen in com.jr7.cystudy.sockets.Server");

    // Not super sure this is worth implementing since usernames should be unique.
    // Plus, can't find a good example of rejecting an incoming connection this way.
    // if(sessionUsernameMap.containsKey(username)){ }

//    if (sessionUsernameMap.size() >= 2) {
//      broadcast("This game already has 2 players, and another tried to join.");
//      onClose(session);
//    }

    if (sessionUsernameMap.isEmpty()) {
      g.player1 = username;
    } else {
      g.player2 = username;
    }

    sessionUsernameMap.put(session, username);
    usernameSessionMap.put(username, session);

    gameService.getQuestions(g, "COMS309");

    String message = "User: " + username + " has Joined the Game";
    broadcast(message);
  }

  private static void broadcast(String message) throws IOException {
    sessionUsernameMap.forEach(
        (session, username) -> {
          synchronized (session) {
            try {
              session.getBasicRemote().sendText(message);
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        });
  }

  /**
   * What happens when a message is sent to the socket.
   *
   * @param session session info
   * @param message string version of the message being sent
   */
  @OnMessage
  public void onMessage(Session session, String message) throws IOException {

    // Handle new messages
    logger.info("Entered into Message: Got Message:" + message);
    String username = sessionUsernameMap.get(session);

    if (message.equalsIgnoreCase("true")
        || message.equalsIgnoreCase("false")
        || message.equalsIgnoreCase("start")) {

      if (g.round == 1) {
        sendTerms();
      }

      if (username.equalsIgnoreCase(g.player1)) {
        sendTerms(g.round, g.player2);
      } else if (username.equalsIgnoreCase(g.player2)) {
        sendTerms(g.round, g.player1);
      }

      if (message.equalsIgnoreCase("true")) {
        if (username.equalsIgnoreCase(g.player1)) {
          g.p1Correct++;
        }
        if (username.equalsIgnoreCase(g.player2)) {
          g.p2Correct++;
        }
      }
      g.round++;
    } else {
      broadcast(username + ": " + message);
    }
  }

  private static void sendTerms() throws IOException {

    List<Terms> roundTerms = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      if (i < g.questions.size() - 1) {
        break;
      }
      roundTerms.add(g.questions.get(i));
    }

    sessionUsernameMap.forEach(
        (session, username) -> {
          synchronized (session) {
            try {
              session.getBasicRemote().sendObject(roundTerms);
            } catch (IOException | EncodeException e) {
              e.printStackTrace();
            }
          }
        });
  }

  private static void sendTerms(int round, String uname) throws IOException {

    int firstCardIdx = round * 4;
    List<Terms> roundTerms = new ArrayList<>();
    for (int i = firstCardIdx; i <= firstCardIdx + 4; i++) {
      if (i < g.questions.size() - 1) {
        break;
      }
      roundTerms.add(g.questions.get(i));
    }

    try {
      usernameSessionMap.get(uname).getBasicRemote().sendObject(roundTerms);
    } catch (IOException | EncodeException e) {
      e.printStackTrace();
    }
  }

  /**
   * What happens when the socket is closed.
   *
   * @param session session info
   */
  @OnClose
  public void onClose(Session session) throws IOException {
    // WebSocket connection closes
    logger.info("Entered into Close");

    String username = sessionUsernameMap.get(session);
    sessionUsernameMap.remove(session);
    usernameSessionMap.remove(username);

    /*TODO
     * Send stats here
     */

    String message = username + " disconnected";
    broadcast(message);
  }

  /**
   * What happens when there's an error.
   *
   * @param session the session
   * @param throwable I think an error?
   */
  @OnError
  public void onError(Session session, Throwable throwable) {
    // Do error handling here
    logger.info("Entered into Error from somewhere");
  }
}
