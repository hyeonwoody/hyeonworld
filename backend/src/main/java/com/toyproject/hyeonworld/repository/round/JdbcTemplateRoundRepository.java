package com.toyproject.hyeonworld.repository.round;

import com.toyproject.hyeonworld.entity.Member;
import com.toyproject.hyeonworld.entity.Party;
import com.toyproject.hyeonworld.entity.Round;
import com.toyproject.hyeonworld.entity.Submission;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateRoundRepository {

    private JdbcTemplate jdbcTemplate;
    public JdbcTemplateRoundRepository (DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public Round getCurrentRound() {
        String sql = "SELECT * FROM round ORDER BY created_at DESC LIMIT 1";


            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                return new Round(rs.getInt("answer"));});

    }



    public void save (Round round){
        String sql = "UPDATE round SET answer = ? WHERE id = ?";
    }

    public void update(Round round) {
        String sql = "UPDATE round SET answer = ? created_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, round.getAnswer(), round.getCreatedAt(), round.getId());
    }


    public Round getCurrentRound(long gameId, int currentRound) {
        String sql = "SELECT * FROM round WHERE game_id = ? AND ";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            return new Round(rs.getInt("answer"));}, gameId, currentRound);
    }


    public void insertCurrentRound(long game_id, int currentRound, Submission submission) {
        String sql = "INSERT INTO round (answer, round, game_id) VALUES (?, ?, ?);";
        jdbcTemplate.update(sql, submission.getNumber(), currentRound, game_id);
    }

    public void updateCurrentRoundAnswer(long gameId, int currentRound, Submission submission) {
        String sql = "UPDATE round SET answer= ? WHERE r.round = ? && r.game_id = ? ";
        jdbcTemplate.update(sql, currentRound, submission.getNumber(), currentRound, gameId);
    }
}
