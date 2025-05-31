package com.healthfintel.backend;

import com.healthfintel.backend.dto.InsuranceHistoryResponseDto;
import com.healthfintel.backend.model.InsuranceHistory;
import com.healthfintel.backend.repository.InsuranceHistoryRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
@Rollback

class BackendApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private InsuranceHistoryRepository insuranceHistoryRepository;

	@Test
	void getInsuranceHistoryByUserId_shouldReturnInsuranceHistory() throws Exception {


		mockMvc.perform(get("/api/insurance-history")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].insurancePolicy.policyId").value(1));
	}
}
