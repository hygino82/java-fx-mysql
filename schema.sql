CREATE TABLE Game (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(100) NOT NULL,
                      genre VARCHAR(40) not null ,
                      platform VARCHAR(40) not null ,
                      release_date DATE not null ,
                      developer VARCHAR(100) not null ,
                      personal_code VARCHAR(30) not null ,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);