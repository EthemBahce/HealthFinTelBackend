package com.healthfintel.backend.controller;


import com.healthfintel.backend.dto.InsuranceHistoryResponseDto;
import com.healthfintel.backend.service.InsuranceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "User Controller", description = "Operations related to Insurance")
public class InsuranceController {

    @Autowired
    InsuranceService insuranceService;

    @GetMapping("/insurance-history")
    @Operation(
            summary = "Get Insurance History by ID",
            description = "Returns an array of insurance history based on the provided user ID"
    )
    public List<InsuranceHistoryResponseDto> InsuranceHistory(){
        return this.insuranceService.getInsuranceHistoryByUserId(1L);
    }
}
