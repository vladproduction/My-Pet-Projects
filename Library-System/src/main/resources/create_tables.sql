-- Use the library database
USE library;

-- Create the authors table
CREATE TABLE IF NOT EXISTS authors (
                                       id INT PRIMARY KEY AUTO_INCREMENT,
                                       name VARCHAR(255) NOT NULL,
                                       nationality VARCHAR(100)
);

-- Create the books table
CREATE TABLE IF NOT EXISTS books (
                                     id INT PRIMARY KEY AUTO_INCREMENT,
                                     title VARCHAR(255) NOT NULL,
                                     author_id INT,
                                     published_year INT,
                                     FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE
);