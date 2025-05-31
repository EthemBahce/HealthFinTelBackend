package com.healthfintel.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double claimAmount;
    private String claimStatus;
    private LocalDateTime dateOfClaim;

    @ManyToOne
    @JoinColumn(name = "insurance_history_id")
    private InsuranceHistory insuranceHistory;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
