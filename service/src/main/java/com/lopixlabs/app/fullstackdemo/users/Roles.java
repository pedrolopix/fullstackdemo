package com.lopixlabs.app.fullstackdemo.users;

import static lombok.AccessLevel.PRIVATE;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

@Data
@NoArgsConstructor()
@AllArgsConstructor(access = PRIVATE)
@Accessors(chain = true)
@EqualsAndHashCode(of = {"id"})
@Builder(toBuilder = true)
public class Roles {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", nullable = false, updatable = false)
    @Setter(AccessLevel.PROTECTED)
    private String id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole name;
}
