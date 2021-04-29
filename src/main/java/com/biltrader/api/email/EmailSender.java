package com.biltrader.api.email;

public interface EmailSender {
    void send(String to, String email);
}
