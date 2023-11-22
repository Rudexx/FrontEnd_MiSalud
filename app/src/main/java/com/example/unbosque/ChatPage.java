package com.example.unbosque;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.helloworld.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ChatPage extends AppCompatActivity {

    private ArrayList<Message> messages;
    private MessageAdapter messageAdapter;
    private ListView chatListView;
    private EditText messageInput;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chat_page);

        messages = new ArrayList<>();
        messageAdapter = new MessageAdapter(this, messages);
        chatListView = findViewById(R.id.chatListView);
        chatListView.setAdapter(messageAdapter);
        messageInput = findViewById(R.id.messageInput);
        sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = messageInput.getText().toString();
                if (!text.isEmpty()) {
                    Message message = new Message(text);
                    messages.add(message);
                    messageAdapter.notifyDataSetChanged();
                    messageInput.setText("");
                }
            }
        });
    }
}
