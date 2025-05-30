package com.healthfintel.backend.controller;


import com.healthfintel.backend.dto.InsuranceHistoryResponseDto;
import com.healthfintel.backend.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InsuranceController {

    @Autowired
    InsuranceService insuranceService;

    @GetMapping("/insurance-history")
    public List<InsuranceHistoryResponseDto> InsuranceHistory(){
        return this.insuranceService.getInsuranceHistoryByUserId(1L);
    }
}
