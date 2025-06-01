package com.healthfintel.backend.controller;


import com.healthfintel.backend.dto.InsuranceHistoryResponseDto;
import com.healthfintel.backend.model.User;
import com.healthfintel.backend.repository.UserRepository;
import com.healthfintel.backend.security.JwtUtils;
import com.healthfintel.backend.service.InsuranceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Tag(name = "Insurance Controller", description = "Operations related to Insurance")
@SecurityRequirement(name = "bearerAuth")
public class InsuranceController {

    @Autowired
    InsuranceService insuranceService;

    @GetMapping("/insurance-history")
    @Operation(
            summary = "Get Insurance History by ID",
            description = "Returns an array of insurance history based on the provided user ID"

    )
    public ResponseEntity<List<InsuranceHistoryResponseDto>> getInsuranceHistory(@RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "").replace("bearer","");


        List<InsuranceHistoryResponseDto> res = insuranceService.getInsuranceHistoryForUser(token);

        if (res.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(res);
    }
}
