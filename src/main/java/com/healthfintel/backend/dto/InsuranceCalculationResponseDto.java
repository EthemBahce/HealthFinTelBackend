package com.healthfintel.backend.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class InsuranceCalculationResponseDto {
    private Double predictedCost;
    private Double predictedCoverage;
    private LocalDateTime calculationDate;
}
