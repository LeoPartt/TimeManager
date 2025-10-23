package eu.epitech.t_dev_700.entities;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(
        name = "tm_user",
        indexes = {
                @Index(name = "idx_user_account_id", columnList = "account_id"),
                @Index(name = "idx_user_first_last", columnList = "first_name, last_name"),
                @Index(name = "idx_user_last", columnList = "last_name")
        }
)
@SQLDelete(sql = "UPDATE tm_user SET deleted_at = now() WHERE id = ?")
@FilterDef(name = "deletedUserFilter", autoEnabled = true)
@Filter(name = "deletedUserFilter", condition = "deleted_at IS NULL")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "account_id", unique = true, foreignKey = @ForeignKey(name = "fk_user_account"))
    private AccountEntity account;

    @NotBlank
    @Size(max = 100)
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @NotBlank
    @Size(max = 100)
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    // citext in Postgres for case-insensitive comparison; keep nullable if optional
    @Column(name = "email", columnDefinition = "VARCHAR")
    private String email;

    @Size(max = 32)
    @Column(name = "phone_number", length = 32)
    private String phoneNumber;

    // Soft delete marker
    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MembershipEntity> memberships = new LinkedHashSet<>();

    public boolean isActive() { return deletedAt == null; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity that)) return false;
        return id != null && id.equals(that.id);
    }
    @Override
    public int hashCode() { return Objects.hashCode(id); }

    @Override
    public String toString() {
        return "User{id=" + id + ", firstName='" + firstName + "', lastName='" + lastName + "'}";
    }
}
