package dev.bank.api.modules.account;

import dev.bank.api.core.contracts.modules.IAccountService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Account findOrCreateAccountByEmail(String email) {
        return accountRepository.findAccountByEmail(email).orElseGet(() -> {
            Account model = new Account();
            model.setEmail(email);
            return accountRepository.save(model);
        });
    }
}
