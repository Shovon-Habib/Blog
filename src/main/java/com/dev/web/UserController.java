package com.dev.web;

import com.dev.dto.requestdto.UserRegistrationDTO;
import com.dev.dto.requestdto.UserStatusDTO;
import com.dev.dto.responsedto.SuccessResponse;
import com.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/create/admin")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        UUID id = userService.createUser(userRegistrationDTO).getId();
        return ResponseEntity.ok().body(new SuccessResponse(id));
    }


}
