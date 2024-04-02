package com.kukhtar.privattesttask.service;

import com.kukhtar.privattesttask.controller.RegistrationController;
import com.kukhtar.privattesttask.dto.UserDTO;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RegistrationServiceTest {
    @MockBean
    HttpSession session;
    @MockBean
    RegistrationController registrationController;
    @Autowired
    RegistrationService registrationService;

    @Test
    public void whenConfirmRegistration_thenAddDataToSession(){
        UserDTO user = new UserDTO();
        user.setUsername("test");
        user.setEmail("test@email.com");
        user.setPassword("password");

        registrationService.confirmRegistration(user, session);

        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);

        Mockito.verify(session, atLeastOnce()).setAttribute(argument.capture(), any(Object.class));
        List<String> values = argument.getAllValues();

        Assertions.assertTrue(values.contains(RegistrationService.CONFIRMATION_CODE));
        Assertions.assertTrue(values.contains(RegistrationService.CURRENT_USER));
    }
}
