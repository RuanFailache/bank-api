package dev.bank.api.modules.account.application.dtos;

import jakarta.validation.constraints.NotBlank;

public record ValidateCodeRequestDto(
        @NotBlank(message = "Code is a required field")
        String code
) {
}
