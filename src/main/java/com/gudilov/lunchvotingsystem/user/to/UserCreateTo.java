package com.gudilov.lunchvotingsystem.user.to;

import com.gudilov.lunchvotingsystem.common.model.HasEmail;
import com.gudilov.lunchvotingsystem.common.to.BaseTo;
import com.gudilov.lunchvotingsystem.user.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

public class UserCreateTo extends BaseTo implements HasEmail, Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(min = 5, max = 32, message = "length must be between 5 and 32 characters")
    private String password;

    private Set<Role> roles;

    public UserCreateTo() {
    }

    public UserCreateTo(Integer id, String name, String email, String password, Set<Role> roles) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    public UserCreateTo(UserCreateTo u){
        this(u.getId(),u.getName(),u.getEmail(),u.getPassword(),u.getRoles());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserCreateTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }
}
