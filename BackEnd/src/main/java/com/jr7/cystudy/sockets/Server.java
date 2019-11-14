package com.jr7.cystudy.sockets;

import com.jr7.cystudy.sockets.Config;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint("/websocket/{username}")
@Component
public class Server {

  // Store all socket session and their corresponding username.
  private static Map<Session, String> sessionUsernameMap = new HashMap<>();
  private static Map<String, Session> usernameSessionMap = new HashMap<>();

  private final Logger logger = LoggerFactory.getLogger(Server.class);


  /*TODO
   * - Check for same username entering game
   * - Check for more than 2 players
   * - multiple threads for other groups of 2 playing
   */
  @OnOpen
  public void onOpen(Session session, @PathParam("username") String username) throws IOException {

    // get session and websocket connection
    logger.info("Entered onOpen in com.jr7.cystudy.sockets.Server");

    //if(sessionUsernameMap.containsKey(username))
    sessionUsernameMap.put(session, username);
    usernameSessionMap.put(username, session);

    String message="User: " + username + " has Joined the Game";
    broadcast(message);

  }

  private static void broadcast(String message) throws IOException {
    sessionUsernameMap.forEach((session, username) -> {
      synchronized (session) {
        try {
          session.getBasicRemote().sendText(message);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }

  /*TODO
   * - Population of terms and buttons
   * - Taking in true and false
   * - Taking in time spent on game
   */
  @OnMessage
  public void onMessage(Session session, String message) throws IOException {

    // Handle new messages
    logger.info("Entered into Message: Got Message:"+message);
    String username = sessionUsernameMap.get(session);

    broadcast(username + ": " + message);

  }

  // TODO
  @OnClose
  public void onClose(Session session) throws IOException {
    // WebSocket connection closes
    logger.info("Entered into Close");

    String username = sessionUsernameMap.get(session);
    sessionUsernameMap.remove(session);
    usernameSessionMap.remove(username);

    String message= username + " disconnected";
    broadcast(message);
  }

  // TODO probably add some more stuff to end the game and tell the user things are borked
  @OnError
  public void onError(Session session, Throwable throwable) {
    // Do error handling here
    logger.info("Entered into Error");
  }
}
