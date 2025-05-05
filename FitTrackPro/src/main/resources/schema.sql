-- Drop tables in correct order to avoid FK constraint issues
DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS workouts;
DROP TABLE IF EXISTS coach_assignments;
DROP TABLE IF EXISTS dietary_plans;
DROP TABLE IF EXISTS goals;
DROP TABLE IF EXISTS foods;
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

-- Roles table
CREATE TABLE roles (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(50) NOT NULL UNIQUE
);

-- Users table
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       role_id BIGINT NOT NULL,
                       active BOOLEAN DEFAULT TRUE,
                       FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- Clients table
CREATE TABLE clients (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         user_id BIGINT NOT NULL,
                         age INT,
                         weight DECIMAL(5,2),
                         height DECIMAL(5,2),
                         FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Foods table
CREATE TABLE foods (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       calories INT NOT NULL,
                       proteins DECIMAL(5,2),
                       fats DECIMAL(5,2),
                       carbs DECIMAL(5,2)
);

-- Goals table
CREATE TABLE goals (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       client_id BIGINT NOT NULL,
                       description TEXT,
                       target_weight DECIMAL(5,2),
                       deadline DATE,
                       FOREIGN KEY (client_id) REFERENCES clients(id)
);

-- Dietary Plans table
CREATE TABLE dietary_plans (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               client_id BIGINT NOT NULL,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (client_id) REFERENCES clients(id)
);

-- Workouts table
CREATE TABLE workouts (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          client_id BIGINT NOT NULL,
                          name VARCHAR(100),
                          duration_minutes INT,
                          intensity VARCHAR(50),
                          FOREIGN KEY (client_id) REFERENCES clients(id)
);

-- Meals table
CREATE TABLE meals (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       dietary_plan_id BIGINT NOT NULL,
                       food_id BIGINT NOT NULL,
                       quantity INT NOT NULL,
                       FOREIGN KEY (dietary_plan_id) REFERENCES dietary_plans(id),
                       FOREIGN KEY (food_id) REFERENCES foods(id)
);

-- Coach Assignments table
CREATE TABLE coach_assignments (
                                   id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                   coach_id BIGINT NOT NULL,
                                   client_id BIGINT NOT NULL,
                                   assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   FOREIGN KEY (coach_id) REFERENCES users(id),
                                   FOREIGN KEY (client_id) REFERENCES clients(id)
);