package dev.bank.api.modules.validationcode.dto;

import dev.bank.api.modules.validationcode.ValidationCode;
import lombok.Data;

@Data
public class SentValidationCodeResponseDto {
    private String idValidationRequest;

    static public SentValidationCodeResponseDto from(ValidationCode validationCode) {
        var dto = new SentValidationCodeResponseDto();
        dto.setIdValidationRequest(validationCode.getId().toString());
        return dto;
    }
}
