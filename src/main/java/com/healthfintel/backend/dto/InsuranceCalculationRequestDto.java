package com.healthfintel.backend.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

import java.util.List;

@Data
public class InsuranceCalculationRequestDto {
    @NotNull
    @Min(0)
    private Integer age;

    @NotBlank
    private String gender;

    @NotBlank
    private String policyType;

    @NotNull
    private List<String> medicalHistory;

    @NotBlank
    private String preferredHospital;
}
