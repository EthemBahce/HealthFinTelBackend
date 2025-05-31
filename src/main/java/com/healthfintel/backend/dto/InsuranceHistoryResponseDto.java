package com.healthfintel.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceHistoryResponseDto {
    private InsurancePolicyDto insurancePolicy;
    private List<InsuranceClaimDto> insuranceClaims;
}
