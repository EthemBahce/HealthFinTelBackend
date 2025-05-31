package com.healthfintel.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsurancePredictionRequest {
    private Integer age;
    private List<String> medicalHistory;
    private String insuranceType;
    private String preferredHospital;
}
