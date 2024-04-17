package com.toyproject.hyeonworld.repository.round;

import com.toyproject.hyeonworld.entity.Round;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static org.junit.jupiter.api.Assertions.*;

class JdbcTemplateRoundRepositoryTest {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateRoundRepositoryTest() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.setUrl("jdbc:mariadb://200.40.8.10:6033/hyeonworld");
        dataSource.setUsername("hyeonworld1");
        dataSource.setPassword("rkwhr");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    void getCurrentRound() {
        Long gameId = 2L;
        int currentRound = 2;
        String sql = "SELECT r.answer FROM round r INNER JOIN game g ON r.game_id = g.id WHERE g.id = ? AND r.round = ? ;";
        Round round = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            return new Round(rs.getInt("answer"));}, gameId, currentRound);
        assert (round.getAnswer() == 2);
    }

    @Test
    void testGetCurrentRound() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void insert() {
    }
}