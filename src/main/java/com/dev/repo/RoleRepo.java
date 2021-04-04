package com.dev.repo;

import com.dev.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface RoleRepo extends JpaRepository<Role, UUID> {

    Optional<Role> findByRoleNameAndDomainStatus(String roleName, boolean status);

    Set<Role> findAllByDomainStatus(boolean status);
}
