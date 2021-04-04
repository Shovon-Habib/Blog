package com.dev.service;


import com.dev.domain.User;
import com.dev.dto.projection.BloggerProjection;
import com.dev.dto.requestdto.UserRegistrationDTO;
import com.dev.dto.requestdto.UserStatusDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    User createUser(UserRegistrationDTO userRegistrationDTO);

    User registerBlogger(UserRegistrationDTO userRegistrationDTO);

    User updateUserStatus(UserStatusDTO userStatusDTO);

    User getUserByUsername(String userName);

    User getUserByEmail(String email);

    List<BloggerProjection> getAllBloggers(Pageable pageable);
}
