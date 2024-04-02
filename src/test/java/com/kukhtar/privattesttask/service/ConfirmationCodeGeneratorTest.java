package com.kukhtar.privattesttask.service;

import com.kukhtar.privattesttask.PrivatTestTaskApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfirmationCodeGeneratorTest {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(PrivatTestTaskApplication.class);
    ConfirmationCodeGenerator codeGenerator = applicationContext.getBean(ConfirmationCodeGenerator.class);

    @Test
    public void whenCallingGenerator_thenProduce6digitsNumber(){
        String code = codeGenerator.generateConfirmationCode();

        Assertions.assertTrue(code.matches("^[0-9]{6}$"));
    }
}
