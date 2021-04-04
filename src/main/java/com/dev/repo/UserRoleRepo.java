package com.dev.repo;

import com.dev.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRoleRepo extends JpaRepository<UserRole, UUID> {

    List<UserRole> findByUser_IdAndDomainStatus(UUID userId, boolean status);
}
