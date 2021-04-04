package com.dev.service;

import com.dev.domain.BlogPost;
import com.dev.dto.projection.BlogPostProjection;
import com.dev.dto.requestdto.BlogPostRequestDto;

import java.util.List;
import java.util.UUID;

public interface BlogPostService {

    List<BlogPostProjection> getAllBlogPost();

    UUID approveBlogPost(UUID postId);

    UUID deleteBlogPost(UUID postId);

    BlogPost requestForBlogPostApproval(BlogPostRequestDto blogPostRequestDto);
}
