package com.healthfintel.backend.service;

import com.healthfintel.backend.client.PythonAIClient;
import com.healthfintel.backend.dto.InsurancePredictionRequest;
import com.healthfintel.backend.dto.InsurancePredictionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsurancePredictionService {
    
    private final PythonAIClient pythonAIClient;

    @Autowired
    public InsurancePredictionService(PythonAIClient pythonAIClient) {
        this.pythonAIClient = pythonAIClient;
    }
    
    public InsurancePredictionResponse predictInsuranceCost(InsurancePredictionRequest request) {
        return pythonAIClient.predictInsuranceCost(request);
    }
}
