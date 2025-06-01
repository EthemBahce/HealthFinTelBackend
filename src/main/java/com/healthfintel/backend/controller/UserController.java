package com.healthfintel.backend.controller;

import com.healthfintel.backend.dto.InsuranceHistoryResponseDto;
import com.healthfintel.backend.dto.UserInfoResponseDto;
import com.healthfintel.backend.repository.UserRepository;
import com.healthfintel.backend.security.JwtUtils;
import com.healthfintel.backend.service.InsuranceService;
import com.healthfintel.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "User Controller", description = "Operations related to User")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping("/user-info")
    @Operation(
            summary = "Get User Details",
            description = "Returns user details"

    )
public UserInfoResponseDto getUserDetails(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.replace("Bearer ", "").replace("bearer","");

        return userService.getUserInfo(token);
    }
}


