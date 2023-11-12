package dev.bank.api.modules.account.infra.database.repositories;

import dev.bank.api.modules.account.infra.database.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findAccountByEmail(String email);
}
