-- User table
CREATE TABLE user (
                      id BIGINT PRIMARY KEY,
                      login BOOLEAN UNIQUE,
                      name VARCHAR(20) UNIQUE,
                      relation INT UNIQUE,
                      party_type INT UNIQUE,
                      email VARCHAR(50),
                      proposition BOOLEAN,
                      nick_name VARCHAR(20),
                      nick_name_proposition BOOLEAN,
                      in_game BOOLEAN
);

-- Party table
CREATE TABLE party (
                       id BIGINT PRIMARY KEY,
                       party_type INT,
                       created_at DATETIME
);

-- Party Dashboard table
CREATE TABLE party_dashboard (
                                 party_id BIGINT,
                                 current_game_id BIGINT,
                                 current_game_stage INT,
                                 PRIMARY KEY (party_id)
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
                       id BIGINT PRIMARY KEY,
                       party_id BIGINT,
                       game_id BIGINT,
                       answer INT,
                       created_at DATETIME
);

-- Submission table
CREATE TABLE submission (
                            id BIGINT PRIMARY KEY,
                            round_id BIGINT UNIQUE,
                            user_id BIGINT,
                            number BIGINT,
                            text VARCHAR(255)
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