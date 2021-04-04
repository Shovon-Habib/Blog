package com.dev.web;

import com.dev.dto.requestdto.UserRegistrationDTO;
import com.dev.dto.requestdto.UserStatusDTO;
import com.dev.dto.responsedto.SuccessResponse;
import com.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class BloggerController {

    @Autowired
    private UserService userService;

    @GetMapping("/blogger/all")
    public ResponseEntity<?> getAllBloggers(Pageable pageable) {
        return ResponseEntity.ok().body(new SuccessResponse(userService.getAllBloggers(pageable)));
    }

    @PostMapping("/blogger/registration")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        UUID id = userService.registerBlogger(userRegistrationDTO).getId();
        return ResponseEntity.ok().body(new SuccessResponse(id));
    }

    @PutMapping("/blogger/change/status")
    public ResponseEntity<?> updateUserStatus(@Valid @RequestBody UserStatusDTO userStatusDTO) {
        return ResponseEntity.ok().body(new SuccessResponse(userService.updateUserStatus(userStatusDTO).getId()));
    }
}
