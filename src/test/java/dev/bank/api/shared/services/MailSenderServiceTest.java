package dev.bank.api.shared.services;

import com.github.javafaker.Faker;
import dev.bank.api.shared.services.MailSenderService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MailSenderServiceTest {
    private static Faker faker;

    @InjectMocks
    private MailSenderService sut;

    @Mock
    private JavaMailSender mailSender;

    @BeforeAll
    public static void setup() {
        faker = new Faker();
    }

    @Test
    @DisplayName("Should ensure 'sendMail' calls 'mailSender.send'")
    public void testSendMail() {
        String fakeMail = faker.internet().emailAddress();
        String fakeSubject = faker.lorem().paragraph(3);
        String fakeContent = faker.lorem().paragraph(10);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(fakeMail);
        message.setSubject(fakeSubject);
        message.setText(fakeContent);

        doNothing().when(mailSender).send(message);

        sut.sendMail(fakeMail, fakeSubject, fakeContent);

        verify(mailSender, atLeastOnce()).send(message);
    }
}
