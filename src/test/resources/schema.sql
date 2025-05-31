CREATE TABLE IF NOT EXISTS users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  email VARCHAR(255),
  password VARCHAR(255),
  age INTEGER,
  gender VARCHAR(50),
  address VARCHAR(255),
  phone_number VARCHAR(50),
  created_at TIMESTAMP,
  updated_at TIMESTAMP
);

CREATE TABLE  IF NOT EXISTS insurance_policy (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  policy_type VARCHAR(255),
  coverage_amount DOUBLE,
  premium_amount DOUBLE,
  valid_from TIMESTAMP,
  valid_until TIMESTAMP,
  user_id BIGINT,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  CONSTRAINT fk_policy_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE  IF NOT EXISTS insurance_history (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  insurance_policy_id BIGINT UNIQUE,
  user_id BIGINT,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  CONSTRAINT fk_history_policy FOREIGN KEY (insurance_policy_id) REFERENCES insurance_policy(id),
  CONSTRAINT fk_history_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE  IF NOT EXISTS insurance_claim (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  claim_amount DOUBLE,
  claim_status VARCHAR(100),
  date_of_claim TIMESTAMP,
  insurance_history_id BIGINT,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  CONSTRAINT fk_claim_history FOREIGN KEY (insurance_history_id) REFERENCES insurance_history(id)
);

CREATE TABLE  IF NOT EXISTS prediction_result (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  predicted_cost DOUBLE,
  predicted_coverage DOUBLE,
  user_id BIGINT,
  calculation_date TIMESTAMP,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  CONSTRAINT fk_prediction_user FOREIGN KEY (user_id) REFERENCES users(id)
);
