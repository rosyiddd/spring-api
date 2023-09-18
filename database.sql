CREATE DATABASE spring_api;

USE spring_api;

CREATE TABLE movies
(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    rating FLOAT,
    image VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
) ENGINE InnoDB;

SELECT *
FROM movies;

DESC movies;

DROP TABLE movies;