CREATE TABLE roles(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50));

INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_USER');

CREATE TABLE users(
id INT PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
password VARCHAR(120) NOT NULL);

CREATE TABLE user_roles(
user_id int,
role_id int,
FOREIGN KEY (user_id) REFERENCES users(id),
FOREIGN KEY (role_id) REFERENCES roles(id),
PRIMARY KEY(user_id, role_id)
);

CREATE TABLE products(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
category VARCHAR(50) NOT NULL);
