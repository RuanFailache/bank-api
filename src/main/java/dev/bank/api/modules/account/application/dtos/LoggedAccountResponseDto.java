package dev.bank.api.modules.account.application.dtos;

import lombok.Data;

@Data
public class LoggedAccountResponseDto {
    private String idAccount;
    private String accessToken;
}
