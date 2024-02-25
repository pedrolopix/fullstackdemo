package com.lopixlabs.app.fullstackdemo.users;

import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.jpa.Roles;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

@Data
@NoArgsConstructor()
@AllArgsConstructor(access = PRIVATE)
@Accessors(chain = true)
@EqualsAndHashCode(of = {"id"})
@Builder(toBuilder = true)
@Entity(name = "_user")
public class User extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", nullable = false, updatable = false)
    @Setter(AccessLevel.PROTECTED)
    private String id;
    @NotNull
    private String username;

    @CreationTimestamp
    private LocalDateTime createAt = LocalDateTime.now();
    @Email
    private String email;

    private Boolean enabled = true;

    private String password;

    @ManyToMany
    public List<Role> roles = new ArrayList<>();

    public static void add(String username, String password, List<Role> role) {
        User user = new User();
        user.username = username;
        user.password = BcryptUtil.bcryptHash(password);
        user.roles = role;
        user.persist();
    }
}
