-- Disable FK checks for clean reinitialization
SET FOREIGN_KEY_CHECKS = 0;

-- Clean up existing data (child tables first)
DELETE FROM coach_assignments;
DELETE FROM workouts;
DELETE FROM goals;
DELETE FROM users;
DELETE FROM goal_types;
DELETE FROM roles;
DELETE FROM meal_items;
DELETE FROM meals;
DELETE FROM foods;

-- Re-enable FK checks
SET FOREIGN_KEY_CHECKS = 1;

-- Begin transaction
START TRANSACTION;

-- Insert roles
INSERT INTO roles (name) VALUES
                             ('ADMIN'),
                             ('COACH'),
                             ('CLIENT');

-- Insert goal types
INSERT INTO goal_types (name) VALUES
                                  ('Weight Loss'),
                                  ('Endurance'),
                                  ('Strength'),
                                  ('Flexibility');

-- Insert users (role_id: 1=ADMIN, 2=COACH, 3=CLIENT)
INSERT INTO users (username, password_hash, role_id) VALUES
                                                         ('admin1', 'hashed_pwd_admin', 1),
                                                         ('coach_john', 'hashed_pwd_john', 2),
                                                         ('coach_emily', 'hashed_pwd_emily', 2),
                                                         ('client_mary', 'hashed_pwd_mary', 3),
                                                         ('client_mike', 'hashed_pwd_mike', 3),
                                                         ('client_sara', 'hashed_pwd_sara', 3),
                                                         ('client_dave', 'hashed_pwd_dave', 3);

-- Assign coaches to clients (coach_id → user.id, user_id → user.id)
-- Note: IDs are based on insertion order above
INSERT INTO coach_assignments (coach_id, user_id) VALUES
                                                      (2, 4),  -- coach_john → client_mary
                                                      (2, 5),  -- coach_john → client_mike
                                                      (3, 6),  -- coach_emily → client_sara
                                                      (3, 7);  -- coach_emily → client_dave

-- Insert example workouts
INSERT INTO workouts (user_id, exercise, reps, duration_minutes, workout_date) VALUES
                                                                                   (4, 'Push Ups', 20, NULL, '2025-04-25'),
                                                                                   (5, 'Running', NULL, 30, '2025-04-26'),
                                                                                   (6, 'Plank', NULL, 10, '2025-04-26'),
                                                                                   (7, 'Cycling', NULL, 45, '2025-04-27');

-- Insert example goals (goal_type_id: 1=Weight Loss, 2=Endurance, etc.)
INSERT INTO goals (user_id, goal_type_id, description, target_value, current_value, deadline) VALUES
                                                                                                  (4, 1, 'Lose 5 kg', 65.0, 68.5, '2025-06-01'),
                                                                                                  (5, 2, 'Run 10km in under 1 hour', 10.0, 6.0, '2025-07-01'),
                                                                                                  (6, 3, 'Increase bench press to 80kg', 80.0, 60.0, '2025-07-15'),
                                                                                                  (7, 4, 'Do 10 min of daily stretching', 10.0, 5.0, '2025-06-15');


-- Foods
INSERT INTO foods (name, calories_per_100g, protein_per_100g, carbs_per_100g, fat_per_100g) VALUES
                                                                                                ('Chicken Breast', 165, 31, 0, 3.6),
                                                                                                ('Brown Rice', 112, 2.6, 23, 0.9),
                                                                                                ('Broccoli', 34, 2.8, 7, 0.4),
                                                                                                ('Oatmeal', 68, 2.4, 12, 1.4),
                                                                                                ('Egg', 155, 13, 1.1, 11);

-- Meals (Assume user_id = 4 is a real client from previous seeds)
INSERT INTO meals (user_id, meal_type, meal_date, notes) VALUES
                                                             (4, 'Lunch', '2025-05-04', 'Post-workout meal'),
                                                             (4, 'Breakfast', '2025-05-04', 'Light start');

-- Meal Items (meal_id = 1 = lunch, 2 = breakfast)
INSERT INTO meal_items (meal_id, food_id, quantity_g) VALUES
                                                          (1, 1, 200),  -- Chicken Breast
                                                          (1, 2, 150),  -- Brown Rice
                                                          (1, 3, 100),  -- Broccoli
                                                          (2, 4, 80),   -- Oatmeal
                                                          (2, 5, 50);   -- Egg


-- Commit transaction
COMMIT;