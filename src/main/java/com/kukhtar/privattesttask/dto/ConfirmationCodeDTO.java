package com.kukhtar.privattesttask.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ConfirmationCodeDTO {

    @Size(min = 6, max = 6, message = "Confirmation code has to consist of 6 characters")
    @NotNull(message = "Confirmation code can not be null")
    String confirmationCode;

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }
}
