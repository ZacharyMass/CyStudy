package com.example.cystudy.ui.fragments.StudentFragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cystudy.MainActivity;
import com.example.cystudy.R;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import static com.example.cystudy.MainActivity.user;

public class StudentGameFragment extends Fragment {

    private WebSocketClient cc;
    private String player1 = ""; // Will be pulled from message
    private String player2 = ""; // Will be pulled from message
    public boolean joined = false;

    public StudentGameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Inflate view
        final View v = inflater.inflate(R.layout.fragment_game, container, false);

        // Define all views for manipulation
        final TextView t = v.findViewById(R.id.term);
        TextView player1Text = v.findViewById(R.id.student);
        TextView player2Text = v.findViewById(R.id.opponentName);
        ProgressBar player1Progress = v.findViewById(R.id.userProgress);
        ProgressBar player2Progress = v.findViewById(R.id.opponentProgress);
        TextView[] answers = new TextView[4];
        answers[0] = v.findViewById(R.id.answer1);
        answers[1] = v.findViewById(R.id.answer2);
        answers[2] = v.findViewById(R.id.answer3);
        answers[3] = v.findViewById(R.id.answer4);
        t.setBackgroundColor(Color.GRAY);

        t.setText("Click to enter game!"); // Temporary placeholder here

        final TextView timeText = v.findViewById(R.id.timer);

        // Initialize timer here
        final CountDownTimer timer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                timeText.setText(millisUntilFinished / 1000 + "");
            }

            public void onFinish() {
                timeText.setText("Out of time!");
            }
        };

        // Waiting timer to buffer a few seconds once both players join
        final CountDownTimer buffer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                t.setClickable(false);
                t.setBackgroundColor(Color.GRAY);
                t.setText("Wait...");
            }

            @Override
            public void onFinish() {
                t.setText("Begin!");
                t.setClickable(true);
                t.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                timer.start();
            }
        };

        Draft[] drafts = {new Draft_6455()};
        String w = "http://coms-309-jr-7.misc.iastate.edu:8080/websocket/";
        w += user;

        try {
            Log.d("Socket:", "Trying socket");
            cc = new WebSocketClient(new URI(w), drafts[0]) {
                int count = 0;
                @Override
                public void onMessage(String message) {

                    Log.d("WebSocket:", message);

                    if (player1.equals("") && !message.substring(0, 5).matches("User:")) { // Edit this to fix string operations
                        String[] msgArray = message.split(":");
                        player1 = msgArray[0];
                        player1Text.setText(player1);
                        t.setText("Player 2 Click to Join!");
                    } else if (player2.equals("") && !message.substring(0, 5).matches("User:")) { // Edit this to fix string operations
                        String[] msgArray = message.split(":");
                        player2 = msgArray[0];
                        Log.d("Player 2", player2);
                        player2Text.setText(player2);
                        buffer.start(); // Start timer once both players are entered
                    } else if (!player1.equals("") && !player2.equals("")) { // player1 and player2 have been assigned, can update ProgressBar now
                        String[] msgArray = message.split(":");
                        String user = msgArray[0];
                        String secondElement = "";
                        if (msgArray.length >= 2) {
                            secondElement = msgArray[1];
                        }
                        String thirdElement = "";
                        if (msgArray.length >= 3) {
                            thirdElement = msgArray[2];
                        }


                        // Fill term and answer
                        if (user.equals("term")) {
                            count = 1;
                            t.setText(secondElement);
                        }
                        if (user.equals("correct")) {
                            answers[0].setText(secondElement);
                        }
                        if (user.equals("incorrect")) {
                            answers[count].setText(secondElement);
                            count++;
                        }

                        // Handle correct messages by updating progress bars
                        if (user.matches(player1)) {
                            player1Progress.incrementProgressBy(20);
                            if (player1Progress.getProgress() == 100) { // Full progress bar
                                timer.cancel(); // Stop timer
                                timeText.setText(player1 + " Wins!");
                                t.setClickable(false); // Disable the term TextView from being clicked again
                            }
                        } else if (user.matches(player2)) {
                            player2Progress.incrementProgressBy(20);
                            if (player2Progress.getProgress() == 100) { // Full progress bar
                                timer.cancel(); // Stop timer
                                timeText.setText((player2 + " Wins!"));
                                t.setClickable(false); // Disable the term TextView from being clicked again
                            }
                        }
                    }
                }

                @Override
                public void onOpen(ServerHandshake handshake) {
                    Log.d("OPEN", "run() returned: " + "is connecting");
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d("CLOSE", "onClose() returned: " + reason);
                }

                @Override
                public void onError(Exception e) {
                    Log.d("Exception:", e.toString());
                }
            };
        } catch (URISyntaxException e) {
            Log.d("Exception:", Objects.requireNonNull(e.getMessage()));
            e.printStackTrace();
        }
        cc.connect();

        answers[0].setOnClickListener((View v15) -> {
            try {
                cc.send(user + ":" + t.getText() + ":correct");
            } catch (Exception e) {
                Log.d("ExceptionSendMessage:", e.getMessage());
            }
        });
        answers[1].setOnClickListener(v14 -> {
            try {
                cc.send(user + ":" + t.getText() + ":incorrect");
            } catch (Exception e) {
                Log.d("ExceptionSendMessage:", e.getMessage());
            }
        });
        answers[2].setOnClickListener(v13 -> {
            try {
                cc.send(user + ":" + t.getText() + ":incorrect");
            } catch (Exception e) {
                Log.d("ExceptionSendMessage:", e.getMessage());
            }
        });
        answers[3].setOnClickListener(v1 -> {
            try {
                cc.send(user + ":" + t.getText() + ":incorrect");
            } catch (Exception e) {
                Log.d("ExceptionSendMessage:", e.getMessage());
            }
        });

        // Set Click Listener
        t.setOnClickListener(v12 -> {
            try {
                if (!joined) {
                    cc.send(user + " clicked the button");
                    joined = true;
                }
            } catch (Exception e) {
                Log.d("ExceptionSendMessage:", e.getMessage());
            }
        });

        return v;
    }

}
