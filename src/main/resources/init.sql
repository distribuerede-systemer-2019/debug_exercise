DROP DATABASE dishackathon;
CREATE DATABASE dishackathon;
Use dishackathon;

CREATE TABLE IF NOT EXISTS customers (
  account_no INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(40) NOT NULL,
  balance INT,
  PRIMARY KEY (account_no)
);

INSERT INTO customers(name,balance) VALUES ("VI <3 DØK", 10000000);