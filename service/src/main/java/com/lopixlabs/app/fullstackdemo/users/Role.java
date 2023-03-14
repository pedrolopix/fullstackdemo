package com.lopixlabs.app.fullstackdemo.users;

import static lombok.AccessLevel.PRIVATE;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Entity(name = "_role")
public class Role extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", nullable = false, updatable = false)
    @Setter(AccessLevel.PROTECTED)
    private String id;

    @CreationTimestamp
    private LocalDateTime createAt = LocalDateTime.now();
    private String role;
}
