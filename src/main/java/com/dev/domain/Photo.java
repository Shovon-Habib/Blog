package com.dev.domain;

import com.dev.utils.custom.annotations.NotEmptyOrNull;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "photo")
public class Photo extends BaseEntity implements Serializable {

    @NotEmptyOrNull(message = "Photo name can not be empty or null")
    @Column(name = "name", nullable = false)
    private String name;
}
