package dev.bank.api.modules.account.application.dtos;

import dev.bank.api.modules.account.infra.database.entities.ValidationCode;
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
