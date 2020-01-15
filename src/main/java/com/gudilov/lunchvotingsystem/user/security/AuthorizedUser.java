package com.gudilov.lunchvotingsystem.user.security;

import com.gudilov.lunchvotingsystem.user.model.User;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private int id;
    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        this.id = user.getId();
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "AuthorizedUser{" +
                "id=" + id +
                "} " + super.toString();
    }
}