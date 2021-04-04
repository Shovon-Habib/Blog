package com.dev.service;

import com.dev.dto.CustomUser;
import com.dev.exceptions.BadRequestExceptionHandler;
import com.dev.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.dev.domain.User user = null;
        if (Utils.isEmail(username)) {
            user = userService.getUserByEmail(username);
        } else {
            user = userService.getUserByUsername(username);
        }

        List<String> roles = loadUserRoles(user.getId());
        if (!checkIfActiveBlogger(user, roles)) {
            throw new BadRequestExceptionHandler("Blogger account not approved yet!",
                    "Blogger account not approved yet!");
        }
        List<GrantedAuthority> authorities = getGrantedAuthorities(roles);
        return new CustomUser(user.getUserName(), user.getPassword(), authorities, user.getId());
    }

    private boolean checkIfActiveBlogger(com.dev.domain.User user, List<String> roles) {
        if (!roles.contains(Utils.ROLE_ADMIN)) {
            return user.isStatus();
        }
        return true;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }

    private List<String> loadUserRoles(UUID userId) {
        return userRoleService.getRoleByUserId(userId);
    }

}