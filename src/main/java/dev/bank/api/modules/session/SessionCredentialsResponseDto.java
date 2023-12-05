package dev.bank.api.modules.session;

import lombok.Data;

@Data
public class SessionCredentialsResponseDto {
    private String idAccount;
    private String accessToken;

    public static SessionCredentialsResponseDto from(Session session) {
        var dto = new SessionCredentialsResponseDto();
        dto.idAccount = session.getAccount().getId().toString();
        dto.accessToken = session.getId().toString();
        return dto;
    }
}
