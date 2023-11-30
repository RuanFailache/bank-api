package dev.bank.api.core.contracts.modules;

import dev.bank.api.modules.account.Account;
import dev.bank.api.modules.session.Session;

public interface ISessionService {
    Session createSessionToAccount(Account account);
}
