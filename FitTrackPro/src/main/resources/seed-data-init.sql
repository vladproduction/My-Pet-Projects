-- Insert roles
INSERT INTO roles (name) VALUES ('ADMIN'), ('COACH'), ('CLIENT');

-- Insert users
INSERT INTO users (username, password, email, role_id, active)
VALUES
    ('admin', 'admin123', 'admin@fittrack.com', 1, true),
    ('coach_john', 'coach123', 'john@fittrack.com', 2, true),
    ('client_anna', 'client123', 'anna@fittrack.com', 3, true),
    ('client_mark', 'markpass', 'mark@fittrack.com', 3, true);

-- Insert clients (referencing user IDs for CLIENTs only)
INSERT INTO clients (user_id, age, weight, height)
VALUES
    (3, 28, 65.5, 170.2),
    (4, 35, 82.3, 180.5);

-- Insert foods
INSERT INTO foods (name, calories, proteins, fats, carbs)
VALUES
    ('Oatmeal', 150, 5.0, 2.5, 27.0),
    ('Chicken Breast', 200, 30.0, 4.0, 0.0),
    ('Broccoli', 50, 4.0, 0.5, 10.0),
    ('Rice', 180, 4.0, 1.0, 40.0);

-- Insert goals
INSERT INTO goals (client_id, description, target_weight, deadline)
VALUES
    (1, 'Lose 5kg in 3 months', 60.0, '2025-08-01'),
    (2, 'Gain 3kg muscle mass', 85.0, '2025-07-15');

-- Insert dietary plans
INSERT INTO dietary_plans (client_id)
VALUES
    (1),
    (2);

-- Insert meals
INSERT INTO meals (dietary_plan_id, food_id, quantity)
VALUES
    (1, 1, 2),  -- Oatmeal
    (1, 2, 1),  -- Chicken Breast
    (1, 3, 1),  -- Broccoli
    (2, 2, 2),  -- Chicken Breast
    (2, 4, 1);  -- Rice

-- Insert workouts
INSERT INTO workouts (client_id, name, duration_minutes, intensity)
VALUES
    (1, 'Cardio Session', 45, 'Medium'),
    (2, 'Strength Training', 60, 'High');

-- Insert coach assignments
INSERT INTO coach_assignments (coach_id, client_id)
VALUES
    (2, 1),
    (2, 2);