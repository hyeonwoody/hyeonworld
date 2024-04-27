package com.toyproject.hyeonworld.repository.submission;

import com.toyproject.hyeonworld.entity.Submission;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static org.junit.jupiter.api.Assertions.*;

class JdbcTemplateSubmissionRepositoryTest {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateSubmissionRepositoryTest() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.setUrl("jdbc:mariadb://200.40.8.10:6033/hyeonworld");
        dataSource.setUsername("hyeonworld1");
        dataSource.setPassword("rkwhr");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    void insert() {
        int game = 0;
        Long memberId = 2L;
        int number = 2;
        String text = "zfzxcvzx,zxczxczxczxc,zxcz";
        String sql = "INSERT INTO submission (game, number, text, member_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, game, number, text, memberId);
    }
    @Test
    void findByMemberId() {
        Long id = 1L;
        String sql = "SELECT * FROM submission WHERE s.member_id = ? ORDER BY s.created_at DESC LIMIT 1";
        jdbcTemplate.update (sql, Submission.class, id);
    }
}