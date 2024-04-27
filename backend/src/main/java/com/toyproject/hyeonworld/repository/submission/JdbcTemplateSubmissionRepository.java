package com.toyproject.hyeonworld.repository.submission;

import com.toyproject.hyeonworld.DTO.Submission.SubmissionDTO;
import com.toyproject.hyeonworld.entity.Submission;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class JdbcTemplateSubmissionRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateSubmissionRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public void insert(SubmissionDTO submissionDTO) {
        String sql = "INSERT INTO submission (game, member_id, number) VALUES (?, ?, ?);";
        jdbcTemplate.update (sql, submissionDTO.getGame(), submissionDTO.getMemberId(), submissionDTO.getNumber());
    }

    public Submission findByMemberId(Long id) {
        String sql = "SELECT * FROM submission WHERE s.member_id = ? ORDER BY s.created_at DESC LIMIT 1";
        return jdbcTemplate.queryForObject (sql, Submission.class, id);
    }

    public String findTextByMemberId(Long id) {
        String sql = "SELECT s.text FROM submission s WHERE s.member_id = ? ORDER BY s.created_at DESC LIMIT 1";
        return jdbcTemplate.queryForObject (sql, String.class, id);
    }
}
