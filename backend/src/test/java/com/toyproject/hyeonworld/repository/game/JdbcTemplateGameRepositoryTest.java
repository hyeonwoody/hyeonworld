package com.toyproject.hyeonworld.repository.game;

import com.toyproject.hyeonworld.entity.Game;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

class JdbcTemplateGameRepositoryTest {

    private JdbcTemplate jdbcTemplate;
    public JdbcTemplateGameRepositoryTest(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.setUrl("jdbc:mariadb://200.40.8.10:6033/hyeonworld");
        dataSource.setUsername("hyeonworld1");
        dataSource.setPassword("rkwhr");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Test
    void findPlayableGames() {
        String sql = "SELECT * FROM game where playable = true";
        List<Game> games =  jdbcTemplate.query(sql, (rs, rowNum) -> {
            int a = 0;
            return new Game(rs.getLong("game_id"), rs.getString("name"), rs.getString("description"));});

        for (Game game : games){
            System.out.println(game.getName());
            System.out.println(game.getDescription());
        }
    }

    @Test
    void findById() {
    }
}