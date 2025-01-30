get the documentation at : http://localhost:8080/swagger-ui/index.html


SQL :

create database csvdb;
show databases;
use csvdb;
CREATE TABLE IF NOT EXISTS user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    contact VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    country VARCHAR(255),
    address VARCHAR(255)
);
select * from user;
