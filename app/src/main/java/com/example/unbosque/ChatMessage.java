package com.example.unbosque;

public class ChatMessage {
    private String text;
    private boolean sent; // Indica si el mensaje fue enviado por el usuario o recibido

    public ChatMessage(String text, boolean sent) {
        this.text = text;
        this.sent = sent;
    }

    public String getText() {
        return text;
    }

    public boolean isSent() {
        return sent;
    }
}