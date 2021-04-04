package com.dev.service.impl;

import com.dev.domain.Role;
import com.dev.domain.User;
import com.dev.domain.UserRole;
import com.dev.exceptions.ResourceNotFoundExceptionHandler;
import com.dev.repo.UserRoleRepo;
import com.dev.service.RoleService;
import com.dev.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepo userRoleRepo;
    @Autowired
    private RoleService roleService;

    @Override
    public List<String> getRoleByUserId(UUID userId) {
        return userRoleRepo.findByUser_IdAndDomainStatus(userId, true)
                .stream().map(userRole -> userRole.getRole().getRoleName())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserRole assignRole(User user, String roleName) {
        UserRole userRole = new UserRole()
                .setUser(user)
                .setRole(getRole(roleName));
        return userRoleRepo.save(userRole);
    }

    private Role getRole(String roleName) {
        return roleService.getRoles().stream()
                .filter(role -> role.equals(roleName))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundExceptionHandler("Role not found!!!"));
    }
}
