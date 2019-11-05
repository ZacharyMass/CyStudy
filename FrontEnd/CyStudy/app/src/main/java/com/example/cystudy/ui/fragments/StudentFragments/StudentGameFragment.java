package com.example.cystudy.ui.fragments.StudentFragments;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class StudentGameFragment extends Fragment {

    private WebSocketClient cc;

    public StudentGameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //Inflate view
        View v = inflater.inflate(R.layout.fragment_game, container, false);
        TextView t = v.findViewById(R.id.term);

        Draft[] drafts = {new Draft_6455()};
        String w = "http://coms-309-jr-7.misc.iastate.edu:8080/websocket/username";

        try {
            Log.d("Socket:", "Trying socket");
            cc = new WebSocketClient(new URI(w), (Draft) drafts[0]) {
                @Override
                public void onMessage(String message) {
                    Log.d("", "run() returned: " + message);
                    t.setText(message);
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
            Log.d("Exception:", e.getMessage().toString());
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
