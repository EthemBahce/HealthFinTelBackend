-- USERS
INSERT INTO users (id, name, email, password, age, gender, address, phone_number, created_at, updated_at) VALUES
(1, 'Batuhan Yalçın', 'batu@gmail.com', 'encrypted-password', 23, 'Boy', 'İstanbul/Sancaktepe', '5550797029', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- INSURANCE POLICY
INSERT INTO insurance_policy (id, policy_type, coverage_amount, premium_amount, valid_from, valid_until, user_id, created_at, updated_at) VALUES
(1, 'Health Insurance', 10000.0, 2000.0, '2024-01-01 00:00:00', '2025-01-01 00:00:00', 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- INSURANCE HISTORY (1-to-1 with insurance_policy)
INSERT INTO insurance_history (id, insurance_policy_id, user_id, created_at, updated_at) VALUES
(1, 1, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- INSURANCE CLAIMS (linked to insurance_history)
INSERT INTO insurance_claim (id, claim_amount, claim_status, date_of_claim, insurance_history_id, created_at, updated_at) VALUES
(1, 5000.0, 'Approved', '2024-06-15 00:00:00', 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- PREDICTION RESULT (linked to user)
INSERT INTO prediction_result (id, predicted_cost, predicted_coverage, user_id, calculation_date, created_at, updated_at) VALUES
(1, 8500.0, 9000.0, 1, '2025-05-11 12:30:00', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- INSURANCE CLAIM (Pending claim for POST /api/insurance-claim response)
INSERT INTO insurance_claim (id, claim_amount, claim_status, date_of_claim, insurance_history_id, created_at, updated_at) VALUES
(2, 5000.0, 'Pending', '2025-05-11 15:30:00', 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
