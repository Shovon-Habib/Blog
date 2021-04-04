package com.dev.service;

import com.dev.domain.User;
import com.dev.domain.UserRole;

import java.util.List;
import java.util.UUID;

public interface UserRoleService {

    List<String> getRoleByUserId(UUID userId);

    UserRole assignRole(User user, String roleName);
}