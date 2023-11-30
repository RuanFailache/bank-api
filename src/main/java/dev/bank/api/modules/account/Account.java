package dev.bank.api.modules.account;

import dev.bank.api.modules.session.Session;
import dev.bank.api.modules.validationcode.ValidationCode;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "TAB_ACCOUNTS")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TA_ID")
    private UUID id;

    @Column(name = "TA_EMAIL", nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<ValidationCode> validationCodes;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Session> sessions;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TA_CREATED_AT")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TA_UPDATED_AT")
    private Date updatedAt;
}
