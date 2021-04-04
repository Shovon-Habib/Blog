package com.dev.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity implements Serializable {

    @Column(name = "comment", nullable = false)
    private String comment;

    @NotNull(message = "BlogPost can not be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private BlogPost blogPost;

    @NotNull(message = "User can not be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
