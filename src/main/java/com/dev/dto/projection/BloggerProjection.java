package com.dev.dto.projection;


import java.util.UUID;

public interface BloggerProjection {

    Boolean getBloggerStatus();

    String getName();

    UUID getId();
}
