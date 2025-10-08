package eu.epitech.t_dev_700.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(
        name = "account",
        indexes = {
                @Index(name = "idx_account_username", columnList = "username")
        }
)
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column(name = "username", nullable = false, unique = true, columnDefinition = "VARCHAR")
    private String username;

    @NotBlank
    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "flags", columnDefinition = "bit varying")
    private String flags;

    @OneToOne(mappedBy = "account")
    @JsonIgnore
    private UserEntity user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountEntity that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }

    @Override
    public String toString() {
        return "Account{id=" + id + ", username='" + username + "'}";
    }
}
