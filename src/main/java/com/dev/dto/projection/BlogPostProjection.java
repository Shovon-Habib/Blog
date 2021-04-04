package com.dev.dto.projection;

import java.util.UUID;

public interface BlogPostProjection {

    public String getBloggerName();

    public String getBloggerUserName();

    public UUID getUserId();

    public UUID getBlogPostId();

    public Boolean getBlogStatus();

    public String getTitle();
}
