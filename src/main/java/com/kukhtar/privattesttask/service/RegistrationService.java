package com.kukhtar.privattesttask.service;

import com.kukhtar.privattesttask.dto.UserDTO;
import com.kukhtar.privattesttask.mapper.UserMapper;
import com.kukhtar.privattesttask.model.User;
import com.kukhtar.privattesttask.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    public static final String CONFIRMATION_CODE = "confirmationCode";
    public static final String CURRENT_USER = "currentUser";
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    ConfirmationCodeGenerator codeGenerator;
    @Autowired
    UserMapper userMapper;

    public void confirmRegistration(UserDTO user, HttpSession session){
        String email = user.getEmail();
        String confirmationCode = codeGenerator.generateConfirmationCode();
        emailService.sendConfirmationEmail(email, confirmationCode);

        if(userRepository.existsUserByEmail(user.getEmail())){
            throw new IllegalStateException("User with this email already exists");
        }

        // Adding confirmation code as a session parameter, so it can be validated in the controller
        // when user sends confirmation request
        session.setAttribute(CONFIRMATION_CODE, confirmationCode);
        session.setAttribute(CURRENT_USER, user);
    }

    public UserDTO registerUser(UserDTO userDTO){
        User user = userRepository.save(userMapper.userDTOToModel(userDTO));
        return userMapper.userModelToDTO(user);
    }

}
