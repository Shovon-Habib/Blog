package com.dev.repo;

import com.dev.domain.BlogPost;
import com.dev.dto.projection.BlogPostProjection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BlogPostRepo extends JpaRepository<BlogPost, UUID> {

    @Query(value = "SELECT " +
            "u.name AS bloggerName, " +
            "u.userName AS bloggerUserName, " +
            "u.id AS userId, " +
            "bp.id AS blogPostId, " +
            "bp.status AS blogStatus, " +
            "bp.title AS title " +
            "FROM BlogPost bp INNER JOIN bp.user u")
    List<BlogPostProjection> getAllBlogPost();

    @Modifying
    @Query(value = "Update blog_post set status = true where id =:postId and domain_status = true", nativeQuery = true)
    Integer approveBlogPost(@Param("postId") UUID postId);

    @Modifying
    @Query(value = "Update blog_post set domain_status = false where id =:postId", nativeQuery = true)
    Integer deleteBlogPost(@Param("postId") UUID postId);
}
