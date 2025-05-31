package com.healthfintel.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class InsuranceClaimDto {
    private Double claimAmount;
    private String claimStatus;
    private LocalDate dateOfClaim;
}
