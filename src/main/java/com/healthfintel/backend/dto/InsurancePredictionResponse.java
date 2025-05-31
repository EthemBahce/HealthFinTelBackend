package com.healthfintel.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsurancePredictionResponse {
    private Double predictedCost;
    private Double predictedCoverage;
}
