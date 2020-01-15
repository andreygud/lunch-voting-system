package com.gudilov.lunchvotingsystem.user.to;

import com.gudilov.lunchvotingsystem.common.model.HasEmail;
import com.gudilov.lunchvotingsystem.common.model.HasPassword;
import com.gudilov.lunchvotingsystem.common.to.BaseTo;
import com.gudilov.lunchvotingsystem.user.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

public class UserCreateTo extends BaseTo implements HasEmail, Serializable, HasPassword {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserCreateTo createTo = (UserCreateTo) o;

        if (name != null ? !name.equals(createTo.name) : createTo.name != null) return false;
        if (email != null ? !email.equals(createTo.email) : createTo.email != null) return false;
        if (password != null ? !password.equals(createTo.password) : createTo.password != null) return false;
        return roles != null ? roles.equals(createTo.roles) : createTo.roles == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }
}
