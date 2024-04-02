package com.kukhtar.privattesttask.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceMock implements EmailService{

    private Logger logger = LoggerFactory.getLogger(EmailServiceMock.class);

    public void sendConfirmationEmail(String email, String confirmationCOde){
        logger.info("Confirmation code for the email: " + email + " Is - " + confirmationCOde +
                "\nPlease go to the link /v1/registration/confirm and pass the code");
    }
}
