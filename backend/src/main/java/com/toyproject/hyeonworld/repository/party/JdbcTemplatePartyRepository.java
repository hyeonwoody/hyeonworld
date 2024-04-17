package com.toyproject.hyeonworld.repository.party;

import com.toyproject.hyeonworld.DTO.PartyInitDTO;
import com.toyproject.hyeonworld.entity.Member;
import com.toyproject.hyeonworld.entity.Party;
import com.toyproject.hyeonworld.entity.Submission;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplatePartyRepository {

    private JdbcTemplate jdbcTemplate;
    public JdbcTemplatePartyRepository (DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int init(PartyInitDTO party) {
        String sql = "INSERT INTO party (party_type, persons, current_game, current_game_stage)\n" +
                "VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                party.getPartyType(),
                party.getPersons(),
                -1,
                0);
    }


    //    @Query("SELECT e.currentGame FROM Party e WHERE e.createdAt = (SELECT MAX(createdAt) FROM Party)")
    public Integer getCurrentGame() {
        String sql = "SELECT current_game FROM party WHERE created_at = (SELECT MAX(created_at) FROM party)";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    //    @Query("SELECT e.currentGameStage FROM Party e WHERE e.createdAt = (SELECT MAX(createdAt) FROM Party)")
    public Integer getCurrentGameStage() {
        String sql = "SELECT current_game FROM party WHERE created_at = (SELECT MAX(created_at) FROM party)";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> rs.getInt("current_game"));
    }

    //    @Query("SELECT e.partyType FROM Party e WHERE e.createdAt = (SELECT MAX(createdAt) FROM Party)")




    public Integer getCurrentPartyType() {
        return null;
    }

    public Member getTarget() {
        String sql = "SELECT m.name FROM member m " +
                "INNER JOIN party p ON p.member_id = m.id " +
                "WHERE p.party_id = (SELECT MAX(id) FROM party)";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                    new Member (rs.getLong("id"), rs.getString("name")));
        } catch ( IncorrectResultSizeDataAccessException error) {
            return null;
        }

    }



    public void open(int game) {
        String sql = "UPDATE party SET current_game = ? WHERE party_id = (SELECT party_id FROM party ORDER BY created_at DESC LIMIT 1);";
        jdbcTemplate.update(sql, game); // Replace ? with game
    }

    public void setCurrentGameStage(Integer gameStage) {
        String sql = "UPDATE party SET current_game = ? WHERE created_at = (SELECT MAX(created_at) FROM party);";
        jdbcTemplate.update(sql, gameStage);
    }

    public void setTarget(String memberName) {
        String sql = "UPDATE party p SET target_id = ( SELECT id FROM member m WHERE m.name = ? ) WHERE p.created_at =  (SELECT MAX(created_at)";
        jdbcTemplate.update(sql, memberName);
    }
}
