package com.dev.repo;

import com.dev.domain.BlogPostPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BlogPostPhotoRepo extends JpaRepository<BlogPostPhoto, UUID> {
}
