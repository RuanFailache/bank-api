package dev.bank.api.modules.validationcode;

import dev.bank.api.modules.account.Account;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

import static java.util.Objects.isNull;

@Data
@Entity
@Table(name = "TAB_VALIDATION_CODES")
public class ValidationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TVC_ID")
    private UUID id;

    @Column(name = "TVC_CODE", nullable = false)
    private String code;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TVC_EXPIRES_AT")
    private Date expiresAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TVC_ID_ACCOUNT")
    private Account account;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TVC_CREATED_AT")
    private Date createdAt;

    @PrePersist
    private void beforeSaveOnDatabase() {
        boolean isCreatedAtNullable = isNull(createdAt);

        if (isCreatedAtNullable) {
            createdAt = new Date();
        }

        boolean isExpiresAtNullable = isNull(expiresAt);

        if (isExpiresAtNullable) {
            long FIVE_MINUTES = 5 * 60 * 1000;
            long createdAtTime = createdAt.getTime();
            expiresAt = new Date(createdAtTime + FIVE_MINUTES);
        }
    }
}
