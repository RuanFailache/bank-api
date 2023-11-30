package dev.bank.api.modules.validationcode;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ValidationCodeRepository extends JpaRepository<ValidationCode, UUID> {
}
