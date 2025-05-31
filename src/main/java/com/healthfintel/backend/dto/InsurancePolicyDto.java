package com.healthfintel.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsurancePolicyDto {
    private Long policyId;
    private String policyType;
    private Double coverageAmount;
    private Double premiumAmount;
    private LocalDate validFrom;
    private LocalDate validUntil;
}
