package com.healthfintel.backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class UserInfoResponseDto {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String gender;
    private String address;
    private String phoneNumber;
    private List<InsuranceHistorySummaryDto> insuranceHistory;
}
