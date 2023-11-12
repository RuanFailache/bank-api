package dev.bank.api.modules.account.domain.services;

import dev.bank.api.modules.account.application.dtos.CredentialsResponseDto;
import dev.bank.api.modules.account.application.dtos.SentValidationCodeResponseDto;
import dev.bank.api.modules.account.application.services.AuthenticationService;
import dev.bank.api.modules.account.infra.database.entities.Account;
import dev.bank.api.modules.account.infra.database.entities.ValidationCode;
import dev.bank.api.modules.account.infra.database.repositories.AccountRepository;
import dev.bank.api.modules.account.infra.database.repositories.ValidationCodeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import java.util.Optional;
import java.util.Random;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private AccountRepository accountRepository;

    private ValidationCodeRepository validationCodeRepository;

    /**
     * Generates a random code with 6 digits
     *
     * @return generated code
     */
    private String generateRandomCode() {
        Random random = new Random();
        int number = random.nextInt(999999);
        return String.format("%06d", number);
    }

    /**
     * Validate if the provided email has an account.
     * - In the case where an account already exists, a validation code will be sent via email to be used at next authentication step.
     * - In the case where an account doesn't exist, an account will be created, and an email with the same content will be sent.
     *
     * @param email: Email to be verified
     *
     * @return Identifier of created validation request
     */
    @Transactional
    public SentValidationCodeResponseDto sendValidationCode(String email) {
        Optional<Account> foundAccount = accountRepository.findAccountByEmail(email);

        Account account = foundAccount.orElseGet(() -> {
            Account model = new Account();
            model.setEmail(email);
            return accountRepository.save(model);
        });

        String generatedCode = generateRandomCode();

        ValidationCode model = new ValidationCode();
        model.setCode(generatedCode);
        model.setAccount(account);
        ValidationCode validationCode = validationCodeRepository.save(model);

        // TODO: send email with validation code

        return SentValidationCodeResponseDto.from(validationCode);
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
