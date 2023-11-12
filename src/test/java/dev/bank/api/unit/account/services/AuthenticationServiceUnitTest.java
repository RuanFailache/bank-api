package dev.bank.api.unit.account.services;

import com.github.javafaker.Faker;
import dev.bank.api.modules.account.application.dtos.SentValidationCodeResponseDto;
import dev.bank.api.modules.account.infra.database.entities.Account;
import dev.bank.api.modules.account.infra.database.entities.ValidationCode;
import dev.bank.api.modules.account.infra.database.repositories.AccountRepository;
import dev.bank.api.modules.account.domain.services.AuthenticationServiceImpl;
import dev.bank.api.modules.account.infra.database.repositories.ValidationCodeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceUnitTest {
    static Faker faker;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ValidationCodeRepository validationCodeRepository;

    @BeforeAll
    static void setup() {
        faker = new Faker();
    }

    private void mockSaveValidationCode() {
        ValidationCode fakeValidationCode = new ValidationCode();
        fakeValidationCode.setId(faker.internet().uuid());
        fakeValidationCode.setCode(faker.number().digits(6));
        when(validationCodeRepository.save(any())).thenReturn(fakeValidationCode);
    }

    @Test
    @DisplayName("Should ensure 'sendValidationCode' calls 'findAccountByEmail'")
    void testSendValidationCode_When_TriesToFindAnAccount() {
        mockSaveValidationCode();

        var email = faker.internet().emailAddress();

        authenticationService.sendValidationCode(email);

        verify(accountRepository, atLeastOnce()).findAccountByEmail(email);
    }

    @Test
    @DisplayName("Should ensure 'sendValidationCode' when account is not found, creates one account")
    void testSendValidationCode_When_NotFoundAccount() {
        mockSaveValidationCode();

        var email = faker.internet().emailAddress();

        var account = new Account();
        account.setEmail(email);

        authenticationService.sendValidationCode(email);

        verify(accountRepository, atLeastOnce()).save(account);
    }

    @Test
    @DisplayName("Should ensure 'sendValidationCode' after generating a code, saves the generated code")
    void testSendValidationCode_When_SavesValidationCode() {
        mockSaveValidationCode();

        authenticationService.sendValidationCode(faker.internet().emailAddress());

        verify(validationCodeRepository, atLeastOnce()).save(any());
    }

    @Test
    @DisplayName("Should ensure 'sendValidationCode' returns 'SentValidationCodeResponseDto'")
    void testSendValidationCode_When_ReturnsSuccessfully() {
        mockSaveValidationCode();

        var result = authenticationService.sendValidationCode(faker.internet().emailAddress());

        assertInstanceOf(SentValidationCodeResponseDto.class, result);
    }
}
