package com.dev.domain;

import com.dev.utils.custom.annotations.NotEmptyOrNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "blog_post")
public class BlogPost extends BaseEntity implements Serializable {

    @NotEmptyOrNull(message = "Blog Title can not be empty or null")
    @Column(name = "title", nullable = false)
    private String title;

    @NotEmptyOrNull(message = "Blog content can not be empty or null")
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "status", nullable = false)
    private boolean status;

    @NotNull(message = "Blogger can not be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
