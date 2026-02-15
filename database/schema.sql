CREATE TABLE Game (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(100) NOT NULL,
                      genre VARCHAR(40) not null ,
                      platform VARCHAR(40) not null ,
                      release_date DATE not null ,
                      developer VARCHAR(100) not null ,
                      personal_code VARCHAR(30) not null unique,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO Game (name, genre, platform, release_date, developer, personal_code) VALUES
-- Mega Drive
('Sonic the Hedgehog', 'Platformer', 'Mega Drive', '1991-06-23', 'Sonic Team', 'SONIC1-MD-006'),
('Streets of Rage 2', 'Beat em up', 'Mega Drive', '1992-12-20', 'Sega', 'SOR2-MD-007'),
('Gunstar Heroes', 'Run and gun', 'Mega Drive', '1993-09-10', 'Treasure', 'GUNSTAR-MD-008'),
('Mortal Kombat II', 'Fighting', 'Mega Drive', '1994-09-09', 'Probe Entertainment', 'MK2-MD-009'),
('Aladdin', 'Platformer', 'Mega Drive', '1993-11-11', 'Virgin Games', 'ALADDIN-MD-010'),
('Phantasy Star IV', 'RPG', 'Mega Drive', '1993-12-17', 'Sega', 'PS4-MD-011'),
('Shinobi III: Return of the Ninja Master', 'Action', 'Mega Drive', '1993-07-23', 'Sega', 'SHINOBI3-MD-012'),
('Comix Zone', 'Beat em up', 'Mega Drive', '1995-08-01', 'Sega', 'COMIX-MD-013'),
('Earthworm Jim', 'Platformer', 'Mega Drive', '1994-10-01', 'Shiny Entertainment', 'EWJ-MD-014'),
('Beyond Oasis', 'Action RPG', 'Mega Drive', '1994-12-16', 'Ancient', 'BOASIS-MD-015'),

-- Super Nintendo (SNES)
('The Legend of Zelda: A Link to the Past', 'Action-adventure', 'Super Nintendo', '1991-11-21', 'Nintendo', 'ZELDA-SNES-016'),
('Super Mario World', 'Platformer', 'Super Nintendo', '1990-11-21', 'Nintendo', 'MARIOW-SNES-017'),
('Chrono Trigger', 'RPG', 'Super Nintendo', '1995-03-11', 'Square', 'CHRONO-SNES-018'),
('Donkey Kong Country', 'Platformer', 'Super Nintendo', '1994-11-21', 'Rare', 'DKC-SNES-019'),
('Super Metroid', 'Action-adventure', 'Super Nintendo', '1994-03-19', 'Nintendo', 'METROID-SNES-020'),
('Street Fighter II Turbo', 'Fighting', 'Super Nintendo', '1993-07-01', 'Capcom', 'SF2T-SNES-021'),
('Secret of Mana', 'Action RPG', 'Super Nintendo', '1993-08-06', 'Square', 'SOMANA-SNES-022'),
('F-Zero', 'Racing', 'Super Nintendo', '1990-11-21', 'Nintendo', 'FZERO-SNES-023'),
('Star Fox', 'Shoot em up', 'Super Nintendo', '1993-02-21', 'Nintendo', 'STARFOX-SNES-024'),
('Mega Man X', 'Action-platformer', 'Super Nintendo', '1993-12-17', 'Capcom', 'MMX-SNES-025');