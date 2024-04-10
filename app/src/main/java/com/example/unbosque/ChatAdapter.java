package com.example.unbosque;

import com.example.helloworld.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatAdapter extends ArrayAdapter<ChatMessage> {
    private static final int TYPE_SENT = 0;
    private static final int TYPE_RECEIVED = 1;

    public ChatAdapter(Context context, ArrayList<ChatMessage> messages) {
        super(context, 0, messages);
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage message = getItem(position);
        return message.isSent() ? TYPE_SENT : TYPE_RECEIVED;
    }

    @Override
    public int getViewTypeCount() {
        return 2; // número de tipos de vistas (enviado y recibido)
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage message = getItem(position);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());

            if (message.isSent()) {
                convertView = inflater.inflate(R.layout.message_item, parent, false);
            } else {
                convertView = inflater.inflate(R.layout.message_system, parent, false);
            }
        }

        TextView messageText = convertView.findViewById(R.id.text_message_body);
        messageText.setText(message.getText());

        return convertView;
    }
}