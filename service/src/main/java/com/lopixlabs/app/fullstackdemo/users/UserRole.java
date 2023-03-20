package com.lopixlabs.app.fullstackdemo.users;

import com.lopixlabs.app.fullstackdemo.security.Roles;
import lombok.Getter;

public enum UserRole {
    USER(Roles.USER),
    ADMIN(Roles.ADMIN);

    @Getter
    private final String roleName;

    UserRole(final String roleName) {
        this.roleName = roleName;
    }
}
