package com.toyproject.hyeonworld.repository.party;

import com.toyproject.hyeonworld.configuration.jdbc.JdbcConfig;
import com.toyproject.hyeonworld.entity.Member;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JdbcTemplatePartyRepositoryTest {


    private JdbcTemplate jdbcTemplate;

    @Value("${my.database.driverClassName}")
    String drvierClassName;

    @Value("${my.database.username}")
    String username;

    @Value("${my.database.password}")
    String password;
    @Value("${my.database.url}")
    String url;
    public JdbcTemplatePartyRepositoryTest () {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.setUrl("jdbc:mariadb://200.40.8.10:6033/hyeonworld");
        dataSource.setUsername("hyeonworld1");
        dataSource.setPassword("rkwhr");
        jdbcTemplate = new JdbcTemplate(dataSource);


//        DriverManagerDataSource mockDataSource = mock(DriverManagerDataSource.class);
//        mockDataSource.setDriverClassName("org.mariadb.jdbc.Driver");
//        mockDataSource.setUrl("jdbc:mariadb://200.40.8.10:6033/hyeonworld");
//        mockDataSource.setUsername("hyeonworld1");
//        mockDataSource.setPassword("rkwhr");
//        jdbcTemplate = new JdbcTemplate(mockDataSource);
    }

    @Test
    void init() {
    }

    @Test
    void findFirstByOrderByCreatedAtDesc() {

    }

    @Test
    void getCurrentGame() {
        String sql = "SELECT current_game FROM party WHERE created_at = (SELECT MAX(created_at) FROM party)";
        //Mockito.when(jdbcTemplate.queryForObject(sql, (rs, rowNum) -> rs.getInt("current_game"))).thenReturn(42);  // Replace 42 with an expected value
        int currentGame = jdbcTemplate.queryForObject(sql, Integer.class);
        assertEquals(42, currentGame);
    }

    @Test
    void setCurrentGame(){
        int gameStage =40;
        String sql = "UPDATE party SET current_game = ? WHERE created_at = (SELECT MAX(created_at) FROM party);";
        jdbcTemplate.update(sql, gameStage);
    }


    @Test
    void getCurrentGameStage() {
    }

    @Test
    void getCurrentPartyType() {
    }

    @Test
    void getTarget() {
        String sql = "SELECT m.id FROM member m " +
                "INNER JOIN party p ON p.member_id = m.id " +
                "WHERE p.created_at = (SELECT MAX(created_at) FROM party)";
        Member member = jdbcTemplate.queryForObject(sql, Member.class);
        assertEquals(member.getId(), 2);
    }

    @Test
    void getRecentOne() {
    }

    @Test
    void open() {
    }

    @Test
    void jdbcTemplateMemberRepository() {
    }

    @Test
    void testInit() {
    }

    @Test
    void testGetCurrentGame() {
    }

    @Test
    void testGetCurrentGameStage() {
    }

    @Test
    void testGetCurrentPartyType() {
    }

    @Test
    void testGetTarget() {
    }

    @Test
    void testOpen() {
    }

    @Test
    void setCurrentGameStage() {
    }

    @Test
    void insertTarget() {
        String memberName = "코카콜라";
        String sql = "UPDATE party p SET p.member_id = ( SELECT m.id FROM member m WHERE m.name = ? ) WHERE p.created_at = (SELECT MAX(created_at) FROM party);";
        jdbcTemplate.update(sql, memberName);
        //String sql = "SELECT id FROM member WHERE name = ?";

        //long a = jdbcTemplate.queryForObject(sql,Long.class,memberName);
        //assertEquals(23L, a);
    }



    @Test
    void testInit1() {
    }

    @Test
    void testGetCurrentGame1() {
    }

    @Test
    void testGetCurrentGameStage1() {
    }

    @Test
    void testGetCurrentPartyType1() {
    }

    @Test
    void testGetTarget1() {
    }

    @Test
    void testOpen1() {
    }

    @Test
    void testSetCurrentGameStage() {
    }

    @Test
    void setTarget() {
    }
}