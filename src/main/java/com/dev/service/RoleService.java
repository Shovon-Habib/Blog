package com.dev.service;

import com.dev.domain.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    Role getRoleByRoleName(String roleName);

    Set<Role> getRoles();
}
