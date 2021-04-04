package com.dev.service.impl;


import com.dev.domain.User;
import com.dev.dto.projection.BloggerProjection;
import com.dev.dto.requestdto.UserRegistrationDTO;
import com.dev.dto.requestdto.UserStatusDTO;
import com.dev.exceptions.ResourceNotFoundExceptionHandler;
import com.dev.repo.UserRepo;
import com.dev.service.UserRoleService;
import com.dev.service.UserService;
import com.dev.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public User createUser(UserRegistrationDTO userRegistrationDTO) {
        User user = copyToEntity(userRegistrationDTO);
        user.setPassword(bCryptPasswordEncoder.encode(userRegistrationDTO.getPassword()));
        if (userRegistrationDTO.isBlogger()) {
            user.setStatus(true);
        }
        user = userRepo.save(user);
        assignRole(user, userRegistrationDTO);
        return user;
    }

    @Override
    public User registerBlogger(UserRegistrationDTO userRegistrationDTO) {
        User user = copyToEntity(userRegistrationDTO);
        user.setPassword(bCryptPasswordEncoder.encode(userRegistrationDTO.getPassword()));
        System.out.println(user.getPassword());
        user.setStatus(false);
        user = userRepo.save(user);
        assignRoleBlogger(user, userRegistrationDTO);
        return user;
    }

    @Override
    public User updateUserStatus(UserStatusDTO userStatusDTO) {
        User user = userRepo.findByIdAndDomainStatus(userStatusDTO.getUserId(), true)
                .orElseThrow(() -> new ResourceNotFoundExceptionHandler("User not found!!",
                        "User not found!!"));
        user.setStatus(userStatusDTO.getStatus());
        return userRepo.save(user);
    }

    @Override
    public User getUserByUsername(String userName) {
        return userRepo.findByUserNameAndDomainStatus(userName, true)
                .orElseThrow(() -> new ResourceNotFoundExceptionHandler(
                        "User not valid!!!", "User not valid!!!"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmailAndDomainStatus(email, true)
                .orElseThrow(() -> new ResourceNotFoundExceptionHandler(
                        "User not valid!!!", "User not valid!!!"));
    }

    @Override
    public List<BloggerProjection> getAllBloggers(Pageable pageable) {
        return userRepo.getAllBloggers(pageable.getPageNumber(), pageable.getPageSize());
    }

    public User copyToEntity(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        BeanUtils.copyProperties(userRegistrationDTO, user);
        return user;
    }

    private void assignRole(User user, UserRegistrationDTO userRegistrationDTO) {

        userRoleService.assignRole(user, Utils.ROLE_ADMIN);
        if (userRegistrationDTO.isBlogger()) {
            assignRoleBlogger(user, userRegistrationDTO);
        }
    }

    private void assignRoleBlogger(User user, UserRegistrationDTO userRegistrationDTO) {
        userRoleService.assignRole(user, Utils.ROLE_BLOGGER);
    }
}
