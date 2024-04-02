package com.kukhtar.privattesttask.controller;

import com.kukhtar.privattesttask.dto.UserDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/registration")
public class RegistrationController {
    Logger logger = LoggerFactory.getLogger(RegistrationController.class);

//  TODO: implement registration service
//  private RegistrationService registrationService;

    @PostMapping
    public UserDTO register(@Valid @RequestBody UserDTO user) {
//        return registrationService.register(request);
        logger.info("registration request passed the validation");
        logger.info("received request"  + user);
        return null;
    }
}
