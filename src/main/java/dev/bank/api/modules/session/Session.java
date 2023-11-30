package dev.bank.api.modules.session;

import dev.bank.api.modules.account.Account;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

import static java.util.Objects.isNull;

@Data
@Entity
@Table(name = "TAB_SESSIONS")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TS_ID")
    private UUID id;

    @Column(name = "TS_EXPIRES_AT")
    private Date expiresAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TS_ID_ACCOUNT")
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
            long TWO_HOURS = 2 * 60 * 60 * 1000;
            long createdAtTime = createdAt.getTime();
            expiresAt = new Date(createdAtTime + TWO_HOURS);
        }
    }
}
