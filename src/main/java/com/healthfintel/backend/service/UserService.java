package com.healthfintel.backend.service;

import com.healthfintel.backend.dto.InsuranceHistoryResponseDto;
import com.healthfintel.backend.dto.InsuranceHistorySummaryDto;
import com.healthfintel.backend.dto.UserInfoResponseDto;
import com.healthfintel.backend.model.User;
import com.healthfintel.backend.repository.UserRepository;
import com.healthfintel.backend.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {


    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    public UserInfoResponseDto getUserInfo(String token) {
        String userId;
        try {
            userId = jwtUtils.getUserIdFromToken(token);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid or expired token");
        }

        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));

        UserInfoResponseDto dto = new UserInfoResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setAge(user.getAge());
        dto.setGender(user.getGender());
        dto.setAddress(user.getAddress());
        dto.setPhoneNumber(user.getPhoneNumber());

        List<InsuranceHistorySummaryDto> historyDtos = user.getInsuranceHistory().stream().map(history -> {
            InsuranceHistorySummaryDto summaryDto = new InsuranceHistorySummaryDto();
            summaryDto.setPolicyId(history.getInsurancePolicy().getId());
            summaryDto.setPolicyType(history.getInsurancePolicy().getPolicyType());
            summaryDto.setCoverageAmount(history.getInsurancePolicy().getCoverageAmount());
            summaryDto.setPremiumAmount(history.getInsurancePolicy().getPremiumAmount());
            return summaryDto;
        }).toList();

        dto.setInsuranceHistory(historyDtos);

        return dto;
    }


}
