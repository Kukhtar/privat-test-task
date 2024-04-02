package com.kukhtar.privattesttask.controller;

import com.kukhtar.privattesttask.dto.ConfirmationCodeDTO;
import com.kukhtar.privattesttask.dto.UserDTO;
import com.kukhtar.privattesttask.service.RegistrationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/registration")
public class RegistrationController {
    private Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public UserDTO register(@Valid @RequestBody UserDTO user, HttpSession session) {
        logger.info("registration request passed the validation");
        registrationService.confirmRegistration(user, session);

        return user;
    }

    @PostMapping(path = "/confirm")
    public UserDTO confirmRegistration(@Valid @RequestBody ConfirmationCodeDTO confirmationCodeDTO, HttpSession session){
        String cachedConfirmationCode = (String) session.getAttribute(RegistrationService.CONFIRMATION_CODE);

        if (confirmationCodeDTO.getConfirmationCode().equals(cachedConfirmationCode)){
            logger.info("Confirmation code is successfully validated");
            UserDTO user = (UserDTO)session.getAttribute(RegistrationService.CURRENT_USER);
            return registrationService.registerUser(user);
        }else{
            throw new IllegalStateException("Confirmation code doesn't match");
        }
    }
}
