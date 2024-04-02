package com.kukhtar.privattesttask.service;

import com.kukhtar.privattesttask.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    public static final String CONFIRMATION_CODE = "confirmationCode";
    public static final String CURRENT_USER = "currentUser";
    @Autowired
    EmailService emailService;
    @Autowired
    ConfirmationCodeGenerator codeGenerator;

    public void confirmRegistration(UserDTO user, HttpSession session){
        String email = user.getEmail();
        String confirmationCode = codeGenerator.generateConfirmationCode();
        emailService.sendConfirmationEmail(email, confirmationCode);

        // todo add check for user uniqueness

        // Adding confirmation code as a session parameter, so it can be validated in the controller
        // when user sends confirmation request
        session.setAttribute(CONFIRMATION_CODE, confirmationCode);
        session.setAttribute(CURRENT_USER, user);
    }

}
