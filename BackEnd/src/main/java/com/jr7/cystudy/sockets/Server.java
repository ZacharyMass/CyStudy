package com.jr7.cystudy.sockets;

import com.jr7.cystudy.model.FakeTerm;
import com.jr7.cystudy.model.Game;
import com.jr7.cystudy.service.GameService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

@ServerEndpoint(value = "/websocket/{username}", configurator = CustomConfigurator.class)
@Component
public class Server {

  // Store all socket session and their corresponding username.
  private static Map<Session, String> sessionUsernameMap = new HashMap<>();
  private static Map<String, Session> usernameSessionMap = new HashMap<>();

  private static Game g = new Game();
  @Autowired GameService gameService;

  private static final Logger logger = LoggerFactory.getLogger(Server.class);

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

    //    if (sessionUsernameMap.isEmpty()) {
    //      g.player1 = username;
    //    } else {
    //      g.player2 = username;
    //    }

    logger.info("trying to make " + username + " either p1 or p2");
    if (g.player1.contains("none")) {
      g.player1 = username;
    } else if (g.player2.contains("none")) {
      g.player2 = username;
    }
    logger.info("p1 is " + g.player1 + " after assignment in onOpen");
    logger.info("p2 is " + g.player1 + " after assignment in onOpen");


    sessionUsernameMap.put(session, username);
    usernameSessionMap.put(username, session);

    logger.info("about to enter getQuestions");
    g.questions = gameService.getQuestions(g, "COMS309");
    logger.info("exited getQuestions");

    String message = "User: " + username + " has Joined the Game";
    logger.info("entering broadcast");
    broadcast(message);
  }

  private static void broadcast(String message) throws IOException {
    sessionUsernameMap.forEach(
        (session, username) -> {
          synchronized (session) {
            try {
              session.getBasicRemote().sendText(message);
            } catch (IOException e) {
              logger.info(e.toString());
              e.printStackTrace();
            }
          }
        });
    logger.info("broadcast worked and is exiting");
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
    logger.info("Entered into onMessage: Got Message: " + message);
    String username = sessionUsernameMap.get(session);

    logger.info("about to check if message contains correct, incorrect, or clicked");
    if (((message.contains("correct")) || message.contains("incorrect"))
        && !message.contains("clicked")) {
      logger.info("congrats your message contains correct or incorrect");

      //      if (g.round == 1) {
      //        sendTerms();
      //      }

      logger.info("about to check username to send terms to");
      if (g.player1 != null && g.player1 != null) {
        if (username.equalsIgnoreCase(g.player1)) {
          logger.info("sending terms to player1 with name: " + g.player1);
          sendTerms(g.round, g.player2);
        } else if (username.equalsIgnoreCase(g.player2)) {
          logger.info("sending terms to player2 with name: " + g.player2);
          sendTerms(g.round, g.player1);
        }
      }

      logger.info("about to increment if answer was correct");
      if (message.contains("correct")) {
        if (username.equalsIgnoreCase(g.player1)) {
          g.p1Correct++;
        }
        if (username.equalsIgnoreCase(g.player2)) {
          g.p2Correct++;
        }
      }
      g.round++;
    } else if (message.contains("clicked")) {
      logger.info("message.contains(clicked) == true");
      if (g.round == 1) {
        sendTerms();
      }
      logger.info("boutta broadcast from clicked shit");
      broadcast(username + ": " + message);
    } else {
      logger.info("message didn't contain any of the shit you're testing for, so you fucked");
      broadcast(username + ": " + message);
    }
  }

  private static void sendTerms() throws IOException {

    FakeTerm roundTerm = new FakeTerm();

    logger.info("abt add terms to send in sendTerms");
    roundTerm.question = g.questions.get(0).getTerm();
    roundTerm.correctAnswer = g.questions.get(0).getAnswer();
    roundTerm.wrongAnswer0 = g.questions.get(1).getAnswer();
    roundTerm.wrongAnswer1 = g.questions.get(2).getAnswer();
    roundTerm.wrongAnswer2 = g.questions.get(3).getAnswer();

    logger.info("exited adding terms to send in sendTerms()");

    logger.info("inside sendTerms() about to do session foreach lambda");
    sessionUsernameMap.forEach(
        (session, username) -> {
          synchronized (session) {
            try {
              logger.info("about to try sending all the roundTerm shit in sendTerms()");
              session.getBasicRemote().sendText(roundTerm.question);
              logger.info("sent " + roundTerm.question);
              session.getBasicRemote().sendText(roundTerm.correctAnswer);
              logger.info("sent " + roundTerm.correctAnswer);
              session.getBasicRemote().sendText(roundTerm.wrongAnswer0);
              logger.info("sent " + roundTerm.wrongAnswer0);
              session.getBasicRemote().sendText(roundTerm.wrongAnswer1);
              logger.info("sent " + roundTerm.wrongAnswer1);
              session.getBasicRemote().sendText(roundTerm.wrongAnswer2);
              logger.info("sent " + roundTerm.wrongAnswer2);
            } catch (IOException e) {
              logger.error(e.toString());
              e.printStackTrace();
            }
          }
        });
    logger.info("finished synced lambda for each bs");
  }

  private void sendTerms(int round, String uname) throws IOException {

    logger.info("inside sendTerms, username is: " + uname);

    int firstCardIdx = round * 4;
    FakeTerm roundTerm = new FakeTerm();

    logger.info("inside sendTerms(arg arg) on firstCardIdx: " + firstCardIdx);
    logger.info("about to enter ternary madness inside sendTerms(arg arg)");

    roundTerm.question = g.questions.get(firstCardIdx).getTerm();
    roundTerm.correctAnswer = g.questions.get(firstCardIdx).getAnswer();
    roundTerm.wrongAnswer0 =
        (firstCardIdx + 1 < g.questions.size())
            ? g.questions.get(firstCardIdx + 1).getAnswer()
            : g.questions.get((firstCardIdx + 1) - (g.questions.size() - 1)).getAnswer();
    roundTerm.wrongAnswer1 =
        (firstCardIdx + 2 < g.questions.size())
            ? g.questions.get(firstCardIdx + 2).getAnswer()
            : g.questions.get((firstCardIdx + 2) - (g.questions.size() - 1)).getAnswer();
    roundTerm.wrongAnswer2 =
        (firstCardIdx + 3 < g.questions.size())
            ? g.questions.get(firstCardIdx + 3).getAnswer()
            : g.questions.get((firstCardIdx + 3) - (g.questions.size() - 1)).getAnswer();
    logger.info("exited ternary madness in sendTerms(arg arg");

    try {
      logger.info("about to try sending q&a  in sendTerms(arg arg)");
      logger.info("username to send shit to is: " + uname);
      usernameSessionMap.get(uname).getBasicRemote().sendText(roundTerm.question);
      logger.info("sent " + roundTerm.question);
      usernameSessionMap.get(uname).getBasicRemote().sendText(roundTerm.correctAnswer);
      logger.info("sent " + roundTerm.correctAnswer);
      usernameSessionMap.get(uname).getBasicRemote().sendText(roundTerm.wrongAnswer0);
      logger.info("sent " + roundTerm.wrongAnswer0);
      usernameSessionMap.get(uname).getBasicRemote().sendText(roundTerm.wrongAnswer1);
      logger.info("sent " + roundTerm.wrongAnswer1);
      usernameSessionMap.get(uname).getBasicRemote().sendText(roundTerm.wrongAnswer2);
      logger.info("sent " + roundTerm.wrongAnswer2);
      logger.info("finished sending q&a in sendTerms(arg arg)");

    } catch (IOException e) {
      logger.error("you fucked up and caught an IOException in sendTerms(arg arg)");
      logger.error(e.toString());
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
    logger.error("Entered into onError");
    logger.error(session.toString());
    logger.error(throwable.toString());
    // logger.error(throwable.getMessage());
    // logger.error(throwable.getLocalizedMessage());
    // logger.error(throwable.getCause().toString());
  }
}
