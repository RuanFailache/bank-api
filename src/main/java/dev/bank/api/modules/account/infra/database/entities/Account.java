package dev.bank.api.modules.account.infra.database.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "TAB_ACCOUNTS")
public class Account {
    @Id
    @GeneratedValue
    @Column(name = "TA_ID")
    private String id;

    @Column(name = "TA_EMAIL", nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<ValidationCode> validationCodes;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TA_CREATED_AT")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TA_UPDATED_AT")
    private Date updatedAt;
}
