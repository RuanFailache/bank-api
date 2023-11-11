package dev.bank.api.modules.account.domain.services;

import dev.bank.api.modules.account.application.dtos.CredentialsResponseDto;
import dev.bank.api.modules.account.application.dtos.SentValidationCodeResponseDto;
import dev.bank.api.modules.account.application.services.AuthenticationService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    /**
     * Validate if the provided email has an account.
     * - In the case where an account already exists, a validation code will be sent via email to be used at next authentication step.
     * - In the case where an account doesn't exist, an account will be created, and an email with the same content will be sent.
     *
     * @param email: Email to be verified
     *
     * @return Identifier of created validation request
     */
    public SentValidationCodeResponseDto sendValidationCode(String email) {
        return null;
    }

    /**
     * Validate if the provided validation request is valid and the provided code is valid for this request.
     *
     * @param idValidationRequest: Identifier of the validation request
     * @param code: Generated code associated to the validation request
     *
     * @return Credentials of the created session
     *
     * @throws NotFound : When the validation request is not found
     * @throws Unauthorized : When the code is invalid
     */
    public CredentialsResponseDto validateAuthenticationCode(String idValidationRequest, String code) {
        return null;
    }
}
