package com.healthfintel.backend.client;

import com.healthfintel.backend.dto.InsurancePredictionRequest;
import com.healthfintel.backend.dto.InsurancePredictionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "python-ai-service", url = "${python.ai.service.url}")
public interface PythonAIClient {
    
    @PostMapping("/api/predict-cost")
    InsurancePredictionResponse predictInsuranceCost(@RequestBody InsurancePredictionRequest request);
}
