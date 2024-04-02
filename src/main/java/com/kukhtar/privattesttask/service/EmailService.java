package com.kukhtar.privattesttask.service;

public interface EmailService {
    void sendConfirmationEmail(String email, String confirmationCode);
}
