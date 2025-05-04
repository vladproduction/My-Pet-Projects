-- Drop existing tables if they exist (for dev convenience)
DROP TABLE IF EXISTS coach_assignments;
DROP TABLE IF EXISTS workouts;
DROP TABLE IF EXISTS goals;
DROP TABLE IF EXISTS goal_types;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

-- Roles
CREATE TABLE roles (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(20) NOT NULL UNIQUE
);

-- Users
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password_hash VARCHAR(255) NOT NULL,
                       role_id INT,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       FOREIGN KEY (role_id) REFERENCES roles(id)
                           ON DELETE RESTRICT
                           ON UPDATE CASCADE
);

-- Coach Assignments
CREATE TABLE coach_assignments (
                                   id INT AUTO_INCREMENT PRIMARY KEY,
                                   coach_id INT NOT NULL,
                                   user_id INT NOT NULL,
                                   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   FOREIGN KEY (coach_id) REFERENCES users(id)
                                       ON DELETE CASCADE
                                       ON UPDATE CASCADE,
                                   FOREIGN KEY (user_id) REFERENCES users(id)
                                       ON DELETE CASCADE
                                       ON UPDATE CASCADE,
                                   UNIQUE (coach_id, user_id) -- Prevent duplicate assignments
);

-- Goal Types (normalized)
CREATE TABLE goal_types (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(50) NOT NULL UNIQUE
);

-- Goals
CREATE TABLE goals (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       user_id INT NOT NULL,
                       goal_type_id INT NOT NULL,
                       description TEXT,
                       target_value DECIMAL(10,2),
                       current_value DECIMAL(10,2),
                       deadline DATE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       FOREIGN KEY (user_id) REFERENCES users(id)
                           ON DELETE CASCADE
                           ON UPDATE CASCADE,
                       FOREIGN KEY (goal_type_id) REFERENCES goal_types(id)
                           ON DELETE RESTRICT
                           ON UPDATE CASCADE
);

-- Workouts
CREATE TABLE workouts (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          user_id INT NOT NULL,
                          exercise VARCHAR(100),
                          reps INT,
                          duration_minutes INT,
                          workout_date DATE,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          FOREIGN KEY (user_id) REFERENCES users(id)
                              ON DELETE CASCADE
                              ON UPDATE CASCADE
);

-- Meal types: Breakfast, Lunch, etc. can be ENUM or controlled in app logic
CREATE TABLE meals (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       user_id INT NOT NULL,
                       meal_type VARCHAR(20) NOT NULL,
                       meal_date DATE NOT NULL,
                       notes TEXT,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       FOREIGN KEY (user_id) REFERENCES users(id)
                           ON DELETE CASCADE
                           ON UPDATE CASCADE
);

-- Master list of foods with nutritional info per 100 grams
CREATE TABLE foods (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL UNIQUE,
                       calories_per_100g DECIMAL(6,2),
                       protein_per_100g DECIMAL(6,2),
                       carbs_per_100g DECIMAL(6,2),
                       fat_per_100g DECIMAL(6,2)
);

-- Link table for what foods are included in each meal and in what quantity
CREATE TABLE meal_items (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            meal_id INT NOT NULL,
                            food_id INT NOT NULL,
                            quantity_g DECIMAL(6,2) NOT NULL,
                            FOREIGN KEY (meal_id) REFERENCES meals(id)
                                ON DELETE CASCADE
                                ON UPDATE CASCADE,
                            FOREIGN KEY (food_id) REFERENCES foods(id)
                                ON DELETE CASCADE
                                ON UPDATE CASCADE
);