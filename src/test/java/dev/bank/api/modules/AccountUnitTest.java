package dev.bank.api.modules;

import com.github.javafaker.Faker;
import dev.bank.api.modules.account.Account;
import dev.bank.api.modules.account.AccountRepository;
import dev.bank.api.modules.account.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AccountUnitTest {
    static Faker faker = new Faker();

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService sut;

    private void mockFindAccountByEmail() {
        Account account = new Account();
        account.setEmail(faker.internet().emailAddress());
        doReturn(Optional.of(account)).when(accountRepository).findAccountByEmail(anyString());
    }

    @DisplayName("Ensure 'findOrCreateAccountByEmail' tries to find an account")
    @Test
    void testFindOrCreateAccountByEmail_When_TriesToFindAnAccount() {
        mockFindAccountByEmail();

        var account = sut.findOrCreateAccountByEmail(faker.internet().emailAddress());

        verify(accountRepository, atLeastOnce()).findAccountByEmail(anyString());

        assertInstanceOf(Account.class, account);
    }

    @DisplayName("Ensure 'findOrCreateAccountByEmail' creates an account when the account is not found")
    @Test
    void testFindOrCreateAccountByEmail_When_AccountIsNotFound() {
        doReturn(Optional.empty()).when(accountRepository).findAccountByEmail(anyString());

        sut.findOrCreateAccountByEmail(faker.internet().emailAddress());

        verify(accountRepository, atLeastOnce()).save(any(Account.class));
    }

    @DisplayName("Ensure 'findOrCreateAccountByEmail' not create an account and return it when the account is found")
    @Test
    void testFindOrCreateAccountByEmail_When_AccountIsFound() {
        mockFindAccountByEmail();

        sut.findOrCreateAccountByEmail(faker.internet().emailAddress());

        verify(accountRepository, never()).save(any(Account.class));
    }
}
