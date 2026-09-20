package com.trinetra.personnel.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trinetra.personnel.R;
import com.trinetra.personnel.adapters.ChatAdapter;
import com.trinetra.personnel.models.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private final List<ChatMessage> messages =
            new ArrayList<>();

    private ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        RecyclerView recyclerChat =
                findViewById(R.id.recyclerChat);

        EditText etChatMessage =
                findViewById(R.id.etChatMessage);

        ImageButton btnSendMessage =
                findViewById(R.id.btnSendMessage);

        adapter = new ChatAdapter(messages);

        recyclerChat.setLayoutManager(
                new LinearLayoutManager(this)
        );

        recyclerChat.setAdapter(adapter);

        /*
         * No artificial AI response is generated here.
         *
         * The current FastAPI backend does not expose a
         * verified /chat endpoint.
         */

        btnSendMessage.setOnClickListener(v -> {

            String text =
                    etChatMessage.getText()
                            .toString()
                            .trim();

            if (text.isEmpty()) {
                return;
            }

            messages.add(
                    new ChatMessage(
                            text,
                            true
                    )
            );

            adapter.notifyItemInserted(
                    messages.size() - 1
            );

            recyclerChat.scrollToPosition(
                    messages.size() - 1
            );

            etChatMessage.setText("");

            /*
             * Backend chat integration will be added only
             * after a real chat API endpoint is available.
             */
        });
    }
}