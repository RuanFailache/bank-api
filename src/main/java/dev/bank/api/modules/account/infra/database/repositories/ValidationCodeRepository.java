package dev.bank.api.modules.account.infra.database.repositories;

import dev.bank.api.modules.account.infra.database.entities.ValidationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValidationCodeRepository extends JpaRepository<ValidationCode, String> {
}
