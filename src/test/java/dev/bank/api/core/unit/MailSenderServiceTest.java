package dev.bank.api.core.unit;

import dev.bank.api.core.services.MailSenderService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MailSenderServiceTest {
    @InjectMocks
    private MailSenderService sut;
}
