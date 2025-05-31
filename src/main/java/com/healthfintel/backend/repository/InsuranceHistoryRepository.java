package com.healthfintel.backend.repository;

import com.healthfintel.backend.model.InsuranceHistory;
import com.healthfintel.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceHistoryRepository extends JpaRepository<InsuranceHistory, Long> {

    List<InsuranceHistory> findByUser(User user);

    List<InsuranceHistory> findByUserId(Long userId);
}
