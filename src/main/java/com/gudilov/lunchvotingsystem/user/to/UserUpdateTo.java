package com.gudilov.lunchvotingsystem.user.to;

import com.gudilov.lunchvotingsystem.common.model.HasEmail;
import com.gudilov.lunchvotingsystem.common.model.HasPassword;
import com.gudilov.lunchvotingsystem.common.to.BaseTo;
import com.gudilov.lunchvotingsystem.user.model.Role;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserUpdateTo extends BaseTo implements HasEmail, Serializable, HasPassword {
    private static final long serialVersionUID = 1L;

    @Size(min = 2, max = 100)
    private String name;

    @Email
    @Size(max = 100)
    private String email;

    @Size(min = 5, max = 32, message = "length must be between 5 and 32 characters")
    private String password;

    private Set<Role> roles;

    public UserUpdateTo(Integer id, String name, String email, String password, Set<Role> roles) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    public UserUpdateTo(UserUpdateTo u){
        this(u.getId(),u.getName(),u.getEmail(),u.getPassword(),u.getRoles());
    }
}
