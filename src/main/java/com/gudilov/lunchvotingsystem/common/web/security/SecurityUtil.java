package com.gudilov.lunchvotingsystem.common.web.security;

import com.gudilov.lunchvotingsystem.common.model.HasPassword;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import static java.util.Objects.requireNonNull;

public class SecurityUtil {

    public static int authorizedUser() {
        return get().getId();
    }

    public static AuthorizedUser safeGet() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        AuthorizedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public static void encodePassword(PasswordEncoder passwordEncoder, HasPassword to) {
        String rawPassword = to.getPassword();
        to.setPassword(StringUtils.hasText(rawPassword) ? passwordEncoder.encode(rawPassword) : rawPassword);
    }
}
