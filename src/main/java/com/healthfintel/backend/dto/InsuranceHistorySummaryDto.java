package com.healthfintel.backend.dto;

import lombok.Data;

@Data
public class InsuranceHistorySummaryDto {
    private Long policyId;
    private String policyType;
    private Double coverageAmount;
    private Double premiumAmount;
}
