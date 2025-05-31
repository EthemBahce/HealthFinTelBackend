package com.healthfintel.backend.controller;


import com.healthfintel.backend.dto.InsuranceHistoryResponseDto;
import com.healthfintel.backend.service.InsuranceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Insurance Controller", description = "Operations related to Insurance")
public class InsuranceController {

    @Autowired
    InsuranceService insuranceService;

    @GetMapping("/insurance-history")
    @Operation(
            summary = "Get Insurance History by ID",
            description = "Returns an array of insurance history based on the provided user ID"
    )
    public ResponseEntity<List<InsuranceHistoryResponseDto>> getInsuranceHistory() {
        List<InsuranceHistoryResponseDto> res = insuranceService.getInsuranceHistoryByUserId(1L);

        if (res.isEmpty()) {
            // Return 404 Not Found
            return ResponseEntity.notFound().build();
        }

        // Return 200 OK with the list
        return ResponseEntity.ok(res);
    }
}
