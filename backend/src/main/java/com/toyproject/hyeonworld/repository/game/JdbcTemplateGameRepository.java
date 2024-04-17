package com.toyproject.hyeonworld.repository.game;

import com.toyproject.hyeonworld.entity.Game;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcTemplateGameRepository {

    private JdbcTemplate jdbcTemplate;
    public JdbcTemplateGameRepository (DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    String sql = "SELECT name FROM member where login = true AND in_game = false";
    public List<Game> findPlayableGames (){
        String sql = "SELECT * FROM game where playable = true";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            int a = 0;
            return new Game(rs.getLong("game_id"), rs.getString("name"), rs.getString("description"));});
    }

    public Game findById(long l) {
        String sql = "SELECT * FROM game where id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            int a = 0;
            return new Game(rs.getLong("game_id"), rs.getString("name"), rs.getString("description"));});
    }
}
