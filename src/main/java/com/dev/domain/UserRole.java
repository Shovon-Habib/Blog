package com.dev.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user_role")
public class UserRole extends BaseEntity implements Serializable {

    @NotNull(message = "User can not be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull(message = "Role can not be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    public UserRole setUser(User user) {
        this.user = user;
        return this;
    }

    public UserRole setRole(Role role) {
        this.role = role;
        return this;
    }
}
