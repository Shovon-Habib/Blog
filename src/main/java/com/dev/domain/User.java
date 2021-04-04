package com.dev.domain;

import com.dev.utils.custom.annotations.NotEmptyOrNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user")
public class User extends BaseEntity implements Serializable {

    @NotEmptyOrNull(message = "Name can not be empty or null")
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmptyOrNull(message = "UserName can not be empty or null")
    @Column(name = "user_name", nullable = false)
    private String userName;

    @NotEmptyOrNull(message = "Email can not be empty or null")
    @Column(name = "email", nullable = false)
    private String email;

    @NotEmptyOrNull(message = "Password can not be empty or null")
    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @Column(name = "status", nullable = false)
    private boolean status;
}
