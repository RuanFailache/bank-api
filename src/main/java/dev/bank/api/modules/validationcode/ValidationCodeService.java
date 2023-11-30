package dev.bank.api.modules.validationcode;

import dev.bank.api.core.contracts.modules.IAccountService;
import dev.bank.api.core.contracts.modules.ISessionService;
import dev.bank.api.core.contracts.modules.IValidationCodeService;
import dev.bank.api.core.contracts.shared.IMailSenderService;
import dev.bank.api.core.exceptions.NotFoundException;
import dev.bank.api.core.exceptions.UnauthorizedException;
import dev.bank.api.modules.account.Account;
import dev.bank.api.modules.session.Session;
import dev.bank.api.shared.utils.DateUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class ValidationCodeService implements IValidationCodeService {
    private final IAccountService accountService;
    private final IMailSenderService mailSenderService;
    private final ISessionService sessionService;
    private final ValidationCodeRepository validationCodeRepository;

    public ValidationCodeService(
            IAccountService accountService,
            IMailSenderService mailSenderService,
            ISessionService sessionService,
            ValidationCodeRepository validationCodeRepository
    ) {
        this.accountService = accountService;
        this.mailSenderService = mailSenderService;
        this.sessionService = sessionService;
        this.validationCodeRepository = validationCodeRepository;
    }

    @Transactional
    public ValidationCode saveValidationCode(Account account) {
        ValidationCode model = new ValidationCode();
        model.setCode(generateRandomCode());
        model.setAccount(account);
        return validationCodeRepository.save(model);
    }

    @Transactional
    public ValidationCode sendValidationCode(String email) {
        Account account = accountService.findOrCreateAccountByEmail(email);
        ValidationCode validationCode = saveValidationCode(account);

        mailSenderService.sendMail(
                email,
                "[BANK] Seu código de confirmação",
                "Seu código de confirmação é %s".formatted(validationCode.getCode())
        );

        return validationCode;
    }

    public Session validateAuthenticationCode(String idValidationRequest, String code) {
        UUID castedIdValidationRequest = UUID.fromString(idValidationRequest);

        ValidationCode validationCode = validationCodeRepository.findById(castedIdValidationRequest)
                .orElseThrow(() -> new NotFoundException("Validation request not found"));

        if (validationCode.getExpiresAt().before(DateUtil.NOW)) {
            throw new UnauthorizedException("Validation request is expired");
        }

        if (!validationCode.getCode().equals(code)) {
            throw new UnauthorizedException("Validation code is incorrect");
        }

        return sessionService.createSessionToAccount(validationCode.getAccount());
    }

    private String generateRandomCode() {
        Random random = new Random();
        int number = random.nextInt(999999);
        return String.format("%06d", number);
    }
}
