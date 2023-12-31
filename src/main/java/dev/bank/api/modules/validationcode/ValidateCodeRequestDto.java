package dev.bank.api.modules.validationcode;

import jakarta.validation.constraints.NotBlank;

public record ValidateCodeRequestDto(
        @NotBlank(message = "Validation request identifier is a required field")
        String idValidationRequest,

        @NotBlank(message = "Code is a required field")
        String code
) {
}
