package com.dev.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "like")
public class Like extends BaseEntity implements Serializable {

    @Column(name = "like_status", nullable = false)
    private boolean likeStatus;

    @NotNull(message = "BlogPost can not be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private BlogPost blogPost;

    @NotNull(message = "User can not be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
