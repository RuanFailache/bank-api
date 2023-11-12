package dev.bank.api.modules.account.infra.database.repositories;

import dev.bank.api.modules.account.infra.database.entities.ValidationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ValidationCodeRepository extends JpaRepository<ValidationCode, UUID> {
}
