-- hyeonworld.game definition

CREATE TABLE `game` (
                        `game_id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `description` varchar(70) NOT NULL,
                        `name` varchar(20) DEFAULT NULL,
                        `playable` bit(1) NOT NULL,
                        PRIMARY KEY (`game_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- hyeonworld.`member` definition

CREATE TABLE `member` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `email` varchar(50) DEFAULT NULL,
                          `login` bit(1) NOT NULL DEFAULT b'0',
                          `party_type` tinyint(4) NOT NULL,
                          `name` varchar(15) NOT NULL,
                          `proposition` bit(1) NOT NULL DEFAULT b'0',
                          `nick_name` varchar(15) DEFAULT NULL,
                          `nick_name_proposition` bit(1) NOT NULL DEFAULT b'0',
                          `in_game` bit(1) NOT NULL DEFAULT b'0',
                          `relation` tinyint(4) NOT NULL,
                          `answer` int(11) NOT NULL DEFAULT 0,
                          `total_score` bigint(20) NOT NULL DEFAULT 0,
                          `player` bit(1) NOT NULL DEFAULT b'1',
                          `member_id` bigint(20) NOT NULL DEFAULT 0,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1231 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- hyeonworld.party definition

CREATE TABLE `party` (
                         `party_id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `party_type` int(11) NOT NULL,
                         `persons` int(11) NOT NULL,
                         `current_game` int(11) NOT NULL DEFAULT -1,
                         `created_at` datetime(6) DEFAULT current_timestamp(6),
                         `current_game_stage` int(11) NOT NULL,
                         `member_id` bigint(20) DEFAULT NULL,
                         PRIMARY KEY (`party_id`),
                         KEY `FKa6so7k4m6ss9blrpshix6qpv6` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- hyeonworld.round definition

CREATE TABLE `round` (
                         `round_id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `answer` int(11) NOT NULL,
                         `round` int(11) NOT NULL,
                         `game_id` bigint(20) DEFAULT NULL,
                         `created_at` datetime(6) DEFAULT NULL,
                         PRIMARY KEY (`round_id`),
                         KEY `FKppxonwn9e98lccy46m2eve67m` (`game_id`),
                         CONSTRAINT `FKppxonwn9e98lccy46m2eve67m` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- hyeonworld.score_source definition

CREATE TABLE `score_source` (
                                `score_source_id` bigint(20) NOT NULL AUTO_INCREMENT,
                                `sa` bigint(20) NOT NULL,
                                `member_id` bigint(20) DEFAULT NULL,
                                PRIMARY KEY (`score_source_id`),
                                KEY `FKkemvotl7lukg64ixagot1dbsh` (`member_id`),
                                CONSTRAINT `FKkemvotl7lukg64ixagot1dbsh` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- hyeonworld.submission definition

CREATE TABLE `submission` (
                              `submission_id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `game` int(11) DEFAULT NULL,
                              `member_id` bigint(20) DEFAULT NULL,
                              `number` int(11) DEFAULT NULL,
                              `text` varchar(255) DEFAULT NULL,
                              `created_at` datetime(6) DEFAULT current_timestamp(6),
                              PRIMARY KEY (`submission_id`),
                              KEY `FKioa9msywum3v9k9bvlakddcei` (`member_id`),
                              CONSTRAINT `FKioa9msywum3v9k9bvlakddcei` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;