package com.dev.domain;

import com.dev.utils.custom.annotations.NotEmptyOrNull;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Table(name = "role")
public class Role extends BaseEntity implements Serializable {

    @NotEmptyOrNull(message = "Role name can not be empty or null")
    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Override
    public boolean equals(Object o) {
        String role = String.valueOf(o);
        return Objects.equals(roleName, role);
    }
}
