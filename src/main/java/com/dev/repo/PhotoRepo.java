package com.dev.repo;

import com.dev.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhotoRepo extends JpaRepository<Photo, UUID> {
}
