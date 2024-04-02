package com.kukhtar.privattesttask.service;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomConfirmationCodeGenerator implements ConfirmationCodeGenerator{
    @Override
    public String generateConfirmationCode() {
        Random random = new Random();
        int number = random.nextInt(999999);

        return String.format("%06d", number);
    }
}
