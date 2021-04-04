package com.dev.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "post_photo")
public class BlogPostPhoto extends BaseEntity implements Serializable {

    @NotNull(message = "BlogPost can not be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private BlogPost blogPost;

    @NotNull(message = "Photo can not be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id")
    private Photo photo;
}
