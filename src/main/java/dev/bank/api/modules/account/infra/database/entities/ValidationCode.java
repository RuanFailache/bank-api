package dev.bank.api.modules.account.infra.database.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

import static java.util.Objects.isNull;

@Data
@Entity
@Table(name = "TAB_VALIDATION_CODES")
public class ValidationCode {
    @Id
    @GeneratedValue
    @Column(name = "TVC_ID")
    private String id;

    @Column(name = "TVC_CODE")
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
            long TWO_HOURS = 2 * 60 * 60;
            long createdAtTime = createdAt.getTime();
            expiresAt = new Date(createdAtTime + TWO_HOURS);
        }
    }
}
