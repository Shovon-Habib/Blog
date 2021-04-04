package com.dev.service.impl;

import com.dev.domain.Role;
import com.dev.exceptions.ResourceNotFoundExceptionHandler;
import com.dev.repo.RoleRepo;
import com.dev.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public Set<Role> roles;

    @Override
    public Role getRoleByRoleName(String roleName) {
        return roleRepo.findByRoleNameAndDomainStatus(roleName, true)
                .orElseThrow(() -> new ResourceNotFoundExceptionHandler("Role not found!!"));
    }

    @Override
    public Set<Role> getRoles() {

        if (this.roles == null || this.roles.isEmpty()) {
            this.roles = roleRepo.findAllByDomainStatus(true);
        }
        return this.roles;
    }
}
