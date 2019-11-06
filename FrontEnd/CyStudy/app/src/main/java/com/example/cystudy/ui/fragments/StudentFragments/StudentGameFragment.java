package com.example.cystudy.ui.fragments.StudentFragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cystudy.MainActivity;
import com.example.cystudy.R;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.w3c.dom.Text;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class StudentGameFragment extends Fragment {

    private WebSocketClient cc;
    private String player1 = ""; // Will be pulled from message
    private String player2 = ""; // Will be pulled from message

    public StudentGameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Inflate view
        final View v = inflater.inflate(R.layout.fragment_game, container, false);
        final TextView t = v.findViewById(R.id.term);
        final TextView timeText = v.findViewById(R.id.timer);

        // Initialize timer here
        final CountDownTimer timer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                timeText.setText(millisUntilFinished / 1000 + "");
            }
            public void onFinish() {
                timeText.setText("Done!");
            }
        };

        Draft[] drafts = {new Draft_6455()};
        String w = "http://coms-309-jr-7.misc.iastate.edu:8080/websocket/username";

        try {
            Log.d("Socket:", "Trying socket");
            cc = new WebSocketClient(new URI(w), drafts[0]) {
                @Override
                public void onMessage(String message) {
                    Log.d("", "run() returned: " + message);
                    t.setText(message);

                    /**
                     * This is temporary until Zach G. can have time to edit what gets sent back
                     */
                    TextView player1Text = v.findViewById(R.id.student);
                    TextView player2Text = v.findViewById(R.id.opponentName);
                    ProgressBar player1Progress = v.findViewById(R.id.userProgress);
                    ProgressBar player2Progress = v.findViewById(R.id.opponentProgress);

                    if (player1.equals("") && !message.substring(5, 13).matches("username")) {
                        String[] msgArray = message.split(" ");
                        player1 = msgArray[1];
                        Log.d("Player 1", player1);
                        player1Text.setText(player1);
                    } else if (player2.equals("") && !message.substring(5, 13).matches("username")) {
                        String[] msgArray = message.split(" ");
                        player2 = msgArray[1];
                        Log.d("Player 2", player2);
                        player2Text.setText(player2);
                        timer.start(); // Start timer once both players are entered
                    } else if (!player1.equals("") && !player2.equals("")) { // player1 and player2 have been assigned, can update ProgressBar now
                        String[] msgArray = message.split(" ");
                        String userClick = msgArray[1];

                        if (userClick.matches(player1)) {
                            player1Progress.incrementProgressBy(20);
                        } else {
                            player2Progress.incrementProgressBy(20);
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

        // Set Click Listener
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cc.send(MainActivity.user + " clicked the button");
                } catch (Exception e) {
                    Log.d("ExceptionSendMessage:", Objects.requireNonNull(e.getMessage()));
                }
            }
        });

        return v;
    }

}
