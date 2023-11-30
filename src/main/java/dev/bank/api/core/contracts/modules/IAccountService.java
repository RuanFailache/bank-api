package dev.bank.api.core.contracts.modules;

import dev.bank.api.modules.account.Account;

public interface IAccountService {
    Account findOrCreateAccountByEmail(String email);
}
