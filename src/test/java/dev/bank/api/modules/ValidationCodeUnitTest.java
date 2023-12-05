package dev.bank.api.modules;

import com.github.javafaker.Faker;
import dev.bank.api.core.contracts.modules.IAccountService;
import dev.bank.api.core.contracts.modules.ISessionService;
import dev.bank.api.core.contracts.shared.IMailSenderService;
import dev.bank.api.modules.account.Account;
import dev.bank.api.modules.validationcode.ValidationCode;
import dev.bank.api.modules.validationcode.ValidationCodeRepository;
import dev.bank.api.modules.validationcode.ValidationCodeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ValidationCodeUnitTest {
    private static final Faker faker = new Faker();

    @Mock
    private IAccountService accountService;

    @Mock
    private IMailSenderService mailSenderService;

    @Mock
    private ISessionService sessionService;

    @Mock
    private ValidationCodeRepository validationCodeRepository;

    @InjectMocks
    private ValidationCodeService sut;

    @DisplayName("Ensure 'createSessionToAccount' saves and return the session")
    @Test
    void testSaveValidationCode() {
        var account = new Account();

        var validationCode = new ValidationCode();
        validationCode.setAccount(account);

        doReturn(validationCode).when(validationCodeRepository).save(any(ValidationCode.class));

        sut.saveValidationCode(account);

        verify(validationCodeRepository, atLeastOnce()).save(any(ValidationCode.class));
    }

    @DisplayName("Ensure 'sendValidationCode' find or create an account")
    @Test
    void testSendValidationCode_When_FindAnAccount() {
        var account = new Account();

        var validationCode = new ValidationCode();
        validationCode.setAccount(account);

        doReturn(account).when(accountService).findOrCreateAccountByEmail(anyString());
        doReturn(validationCode).when(validationCodeRepository).save(any(ValidationCode.class));
        doNothing().when(mailSenderService).sendMail(anyString(), anyString(), anyString());

        sut.sendValidationCode(faker.internet().emailAddress());

        verify(accountService, atLeastOnce()).findOrCreateAccountByEmail(anyString());
    }

    @DisplayName("Ensure 'sendValidationCode' saves validation code")
    @Test
    void testSendValidationCode_When_SavesValidationCode() {
        var account = new Account();

        var validationCode = new ValidationCode();
        validationCode.setAccount(account);

        doReturn(account).when(accountService).findOrCreateAccountByEmail(anyString());
        doReturn(validationCode).when(validationCodeRepository).save(any(ValidationCode.class));
        doNothing().when(mailSenderService).sendMail(anyString(), anyString(), anyString());

        sut.sendValidationCode(faker.internet().emailAddress());

        verify(validationCodeRepository, atLeastOnce()).save(any(ValidationCode.class));
    }

    @DisplayName("Ensure 'sendValidationCode' send the mail")
    @Test
    void testSendValidationCode_When_SendMail() {
        var account = new Account();

        var validationCode = new ValidationCode();
        validationCode.setAccount(account);

        doReturn(account).when(accountService).findOrCreateAccountByEmail(anyString());
        doReturn(validationCode).when(validationCodeRepository).save(any(ValidationCode.class));
        doNothing().when(mailSenderService).sendMail(anyString(), anyString(), anyString());

        sut.sendValidationCode(faker.internet().emailAddress());

        verify(mailSenderService, atLeastOnce()).sendMail(anyString(), anyString(), anyString());
    }

    @DisplayName("Ensure 'sendValidationCode' returns correctly")
    @Test
    void testSendValidationCode() {
        var account = new Account();

        var validationCode = new ValidationCode();
        validationCode.setAccount(account);

        doReturn(account).when(accountService).findOrCreateAccountByEmail(anyString());
        doReturn(validationCode).when(validationCodeRepository).save(any(ValidationCode.class));
        doNothing().when(mailSenderService).sendMail(anyString(), anyString(), anyString());

        var res = sut.sendValidationCode(faker.internet().emailAddress());

        assertInstanceOf(ValidationCode.class, res);
    }
}
