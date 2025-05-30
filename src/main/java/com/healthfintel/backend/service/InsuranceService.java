package com.healthfintel.backend.service;

import com.healthfintel.backend.dto.InsuranceClaimDto;
import com.healthfintel.backend.dto.InsuranceHistoryResponseDto;
import com.healthfintel.backend.dto.InsurancePolicyDto;
import com.healthfintel.backend.model.InsuranceHistory;
import com.healthfintel.backend.model.InsurancePolicy;
import com.healthfintel.backend.model.User;
import com.healthfintel.backend.repository.InsuranceHistoryRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsuranceService {


    private final InsuranceHistoryRepository insuranceHistoryRepository;

    public InsuranceService(InsuranceHistoryRepository insuranceHistoryRepository) {
        this.insuranceHistoryRepository = insuranceHistoryRepository;
    }

    public List<InsuranceHistory> getInsuranceHistoryForUser(User user) {
        return insuranceHistoryRepository.findByUser(user);
    }

    // Or by user id
    public List<InsuranceHistoryResponseDto> getInsuranceHistoryByUserId(Long userId) {
        List<InsuranceHistory> insuranceHistories = insuranceHistoryRepository.findByUserId(userId);

        return insuranceHistories.stream().map(history -> {
            InsurancePolicy policy = history.getInsurancePolicy();
            InsurancePolicyDto policyDto = new InsurancePolicyDto(
                    policy.getId(),
                    policy.getPolicyType(),
                    policy.getCoverageAmount(),
                    policy.getPremiumAmount(),
                    policy.getValidFrom().toLocalDate(),
                    policy.getValidUntil().toLocalDate()
            );

            // Map list of InsuranceClaim entities to DTOs
            List<InsuranceClaimDto> claimDtos = history.getPreviousClaims().stream().map(claim ->
                    new InsuranceClaimDto(
                            claim.getClaimAmount(),
                            claim.getClaimStatus(),
                            claim.getDateOfClaim().toLocalDate()
                    )
            ).collect(Collectors.toList());

            // Construct and return InsuranceHistoryResponseDto
            return new InsuranceHistoryResponseDto(policyDto, claimDtos);
        }).collect(Collectors.toList());
    }

}