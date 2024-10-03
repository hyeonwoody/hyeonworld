-- User table
CREATE TABLE user (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      login BOOLEAN,
                      name VARCHAR(20),
                      relation INT,
                      relation_type INT,
                      email VARCHAR(50),
                      proposition BOOLEAN,
                      nickname VARCHAR(20),
                      nickname_proposition BOOLEAN,
                      in_game BOOLEAN,
                      player BOOLEAN,
                      UNIQUE (login, name, relation, relation_type)
);

-- Party table
CREATE TABLE party (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       relation_type INT,
                       created_at DATETIME,
                        terminated_at DATETIME
);

-- Party Dashboard table
CREATE TABLE party_dashboard (
                                 party_id BIGINT PRIMARY KEY,
                                 current_game_id BIGINT,
                                 current_game_stage INT
);

-- Game table
CREATE TABLE game (
                      id BIGINT PRIMARY KEY,
                      name VARCHAR(20),
                      description TEXT,
                      playable BOOLEAN
);

-- Round table
CREATE TABLE round (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       party_id BIGINT,
                       game_id BIGINT,
                       answer BIGINT,
                       created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                        terminated_at DATETIME,
                       UNIQUE (party_id, id)
);

-- Submission table
CREATE TABLE submission (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            round_id BIGINT,
                            user_id BIGINT,
                            number BIGINT,
                            text VARCHAR(255),
                            created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                            UNIQUE (round_id, user_id, created_at)
);

-- Score table
CREATE TABLE score (
                       party_id BIGINT,
                       user_id BIGINT,
                       score BIGINT,
                       PRIMARY KEY (party_id, user_id)
);

-- Score History table
CREATE TABLE score_history (
                               user_id BIGINT,
                               party_id BIGINT,
                               round_id BIGINT,
                               score BIGINT,
                               PRIMARY KEY (user_id, party_id, round_id)
);