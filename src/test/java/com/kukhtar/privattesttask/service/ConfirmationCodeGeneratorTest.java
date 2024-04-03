package com.kukhtar.privattesttask.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConfirmationCodeGeneratorTest {

    @Autowired
    ConfirmationCodeGenerator codeGenerator;

    @Test
    public void whenCallingGenerator_thenProduce6digitsNumber(){
        String code = codeGenerator.generateConfirmationCode();

        Assertions.assertTrue(code.matches("^[0-9]{6}$"));
    }
}
