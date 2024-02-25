package com.lopixlabs.app.fullstackdemo.users;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRegistry implements PanacheRepositoryBase<User, String> {
    public User getUser(final String username, final String password) {
        final PanacheQuery<User> userQuery = find("username= ?1", username);
        return userQuery.stream()
            .findFirst()
            .filter(u->u.getPassword().equals(password))
            .orElse(null);
    }
}
