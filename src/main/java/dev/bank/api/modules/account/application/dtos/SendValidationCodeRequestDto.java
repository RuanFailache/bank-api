package dev.bank.api.modules.account.application.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SendValidationCodeRequestDto(
        @NotBlank(message = "Email is a required field")
        @Email(message = "Email must be valid")
        String email
) {
}
