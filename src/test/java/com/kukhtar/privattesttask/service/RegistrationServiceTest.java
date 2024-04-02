package com.kukhtar.privattesttask.service;

import com.kukhtar.privattesttask.controller.RegistrationController;
import com.kukhtar.privattesttask.dto.UserDTO;
import com.kukhtar.privattesttask.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RegistrationServiceTest {
    @MockBean
    HttpSession session;
    @MockBean
    RegistrationController registrationController;
    @MockBean
    UserRepository userRepository;
    @Autowired
    RegistrationService registrationService;

    @Test
    public void whenConfirmRegistration_thenAddDataToSession(){

        UserDTO user = createUser();
        registrationService.confirmRegistration(user, session);

        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);

        Mockito.verify(session, atLeastOnce()).setAttribute(argument.capture(), any(Object.class));
        List<String> values = argument.getAllValues();

        Assertions.assertTrue(values.contains(RegistrationService.CONFIRMATION_CODE));
        Assertions.assertTrue(values.contains(RegistrationService.CURRENT_USER));
    }

    @Test
    public void whenUserAlreadyExists_thenException(){
        UserDTO user = createUser();
        Mockito.when(userRepository.existsUserByEmail(anyString())).thenReturn(true);

        IllegalStateException thrown = assertThrows(
                IllegalStateException.class,
                () -> registrationService.confirmRegistration(user, session),
                "Expected to throw exception, but it didn't"
        );

        Assertions.assertTrue(thrown.getMessage().contains("User with this email already exists"));
    }

    private UserDTO createUser(){
        UserDTO user = new UserDTO();
        user.setUsername("test");
        user.setEmail("test@email.com");
        user.setPassword("password");
        return user;
    }
}
