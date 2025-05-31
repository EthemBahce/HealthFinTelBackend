package com.healthfintel.backend.service;

import com.healthfintel.backend.client.PythonAIClient;
import com.healthfintel.backend.dto.InsurancePredictionRequest;
import com.healthfintel.backend.dto.InsurancePredictionResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InsurancePredictionServiceTest {

    @Mock
    private PythonAIClient pythonAIClient;

    @InjectMocks
    private InsurancePredictionService insurancePredictionService;

    @Test
    void testPredictInsuranceCost() {
        // Given
        InsurancePredictionRequest request = new InsurancePredictionRequest();
        request.setAge(23);
        request.setMedicalHistory(Arrays.asList("Diabetes"));
        request.setInsuranceType("Health Insurance");
        request.setPreferredHospital("Private");

        InsurancePredictionResponse expectedResponse = new InsurancePredictionResponse();
        expectedResponse.setPredictedCost(8500.0);
        expectedResponse.setPredictedCoverage(9000.0);

        when(pythonAIClient.predictInsuranceCost(any())).thenReturn(expectedResponse);

        // When
        InsurancePredictionResponse actualResponse = insurancePredictionService.predictInsuranceCost(request);

        // Then
        assertEquals(expectedResponse.getPredictedCost(), actualResponse.getPredictedCost());
        assertEquals(expectedResponse.getPredictedCoverage(), actualResponse.getPredictedCoverage());
    }
}
