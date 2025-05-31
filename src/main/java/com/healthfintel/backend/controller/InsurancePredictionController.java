package com.healthfintel.backend.controller;

import com.healthfintel.backend.dto.InsurancePredictionRequest;
import com.healthfintel.backend.dto.InsurancePredictionResponse;
import com.healthfintel.backend.service.InsurancePredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/insurance")
public class InsurancePredictionController {
    
    private final InsurancePredictionService insurancePredictionService;

    @Autowired
    public InsurancePredictionController(InsurancePredictionService insurancePredictionService) {
        this.insurancePredictionService = insurancePredictionService;
    }
    
    @PostMapping("/predict")
    public ResponseEntity<InsurancePredictionResponse> predictInsuranceCost(
            @RequestBody InsurancePredictionRequest request) {
        return ResponseEntity.ok(insurancePredictionService.predictInsuranceCost(request));
    }
}
