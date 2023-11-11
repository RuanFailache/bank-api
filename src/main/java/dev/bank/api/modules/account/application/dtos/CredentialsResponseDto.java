package dev.bank.api.modules.account.application.dtos;

import lombok.Data;

@Data
public class CredentialsResponseDto {
    private String idAccount;
    private String accessToken;
}
