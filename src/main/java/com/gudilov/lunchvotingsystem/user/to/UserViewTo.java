package com.gudilov.lunchvotingsystem.user.to;

import com.gudilov.lunchvotingsystem.common.model.HasEmail;
import com.gudilov.lunchvotingsystem.common.to.BaseTo;
import com.gudilov.lunchvotingsystem.user.model.Role;
import lombok.*;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserViewTo extends BaseTo implements HasEmail, Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private String email;

    private boolean enabled;

    private LocalDateTime registered;

    private Set<Role> roles;

    public UserViewTo(Integer id, String name, String email, boolean enabled, LocalDateTime registered, Set<Role> roles) {
        super(id);
        this.name = name;
        this.email = email;
        this.enabled = enabled;
        this.registered = registered;
        this.roles = roles;
    }

    public UserViewTo(UserViewTo u){
        this(u.getId(),u.getName(),u.getEmail(),u.isEnabled(),u.getRegistered(),u.getRoles());
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }
}