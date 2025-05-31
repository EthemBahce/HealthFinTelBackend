package com.healthfintel.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PredictionResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double predictedCost;
    private Double predictedCoverage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime calculationDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
