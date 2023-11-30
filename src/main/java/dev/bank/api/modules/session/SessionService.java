package dev.bank.api.modules.session;

import dev.bank.api.core.contracts.modules.ISessionService;
import dev.bank.api.modules.account.Account;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class SessionService implements ISessionService {
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Transactional
    public Session createSessionToAccount(Account account) {
        Session session = new Session();
        session.setAccount(account);
        return sessionRepository.save(session);
    }
}
