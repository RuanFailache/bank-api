package dev.bank.api.modules.account.application.services;

import dev.bank.api.core.exceptions.NotFoundException;
import dev.bank.api.modules.account.application.dtos.CredentialsResponseDto;
import dev.bank.api.modules.account.application.dtos.SentValidationCodeResponseDto;
import jakarta.mail.internet.AddressException;

public interface AuthenticationService {
    SentValidationCodeResponseDto sendValidationCode(String email);
    CredentialsResponseDto validateAuthenticationCode(String idValidationRequest, String code) throws NotFoundException;
}
