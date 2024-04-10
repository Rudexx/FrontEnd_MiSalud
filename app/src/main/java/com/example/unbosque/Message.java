package com.example.unbosque;

public class Message {
    private String text;
    private boolean isUserMessage; // Nuevo campo booleano

    public Message(String text, boolean isUserMessage) {
        this.text = text;
        this.isUserMessage = isUserMessage;
    }

    public String getText() {
        return text;
    }

    public boolean isUserMessage() {
        return isUserMessage;
    }
}