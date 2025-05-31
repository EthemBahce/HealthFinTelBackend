package com.healthfintel.backend.mock;

import com.healthfintel.backend.dto.InsurancePredictionRequest;
import com.healthfintel.backend.dto.InsurancePredictionResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock-python")
public class MockPythonApiController {
    
    @PostMapping("/api/predict-cost")
    public InsurancePredictionResponse mockPredictCost(@RequestBody InsurancePredictionRequest request) {

        double baseCost = 5000.0;
        double baseCoverage = 7000.0;
        

        double ageFactor = request.getAge() > 50 ? 1.5 : 1.0;
        

        double medicalHistoryFactor = request.getMedicalHistory() != null && !request.getMedicalHistory().isEmpty() ? 1.3 : 1.0;
        

        double hospitalFactor = "Private".equalsIgnoreCase(request.getPreferredHospital()) ? 1.2 : 1.0;
        
        InsurancePredictionResponse response = new InsurancePredictionResponse();
        response.setPredictedCost(baseCost * ageFactor * medicalHistoryFactor * hospitalFactor);
        response.setPredictedCoverage(baseCoverage * ageFactor);
        
        return response;
    }
}
