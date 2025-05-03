-- Disable FK checks for clean reinitialization
SET FOREIGN_KEY_CHECKS = 0;

-- Clean up existing data (child tables first)
DELETE FROM coach_assignments;
DELETE FROM workouts;
DELETE FROM goals;
DELETE FROM users;
DELETE FROM goal_types;
DELETE FROM roles;

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

-- Commit transaction
COMMIT;