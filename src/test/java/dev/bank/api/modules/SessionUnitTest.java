package dev.bank.api.modules;

import dev.bank.api.modules.account.Account;
import dev.bank.api.modules.session.Session;
import dev.bank.api.modules.session.SessionRepository;
import dev.bank.api.modules.session.SessionService;
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
public class SessionUnitTest {
    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private SessionService sut;

    @DisplayName("Ensure 'createSessionToAccount' saves and return the session")
    @Test
    void testCreateSessionToAccount() {
        doReturn(Optional.of(new Session())).when(sessionRepository).save(any(Session.class));

        sut.createSessionToAccount(new Account());

        verify(sessionRepository, atLeastOnce()).save(any(Session.class));
    }
}
