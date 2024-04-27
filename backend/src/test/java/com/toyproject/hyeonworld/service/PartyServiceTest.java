package com.toyproject.hyeonworld.service;

import com.toyproject.hyeonworld.configuration.jdbc.JdbcConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static org.junit.jupiter.api.Assertions.*;

class PartyServiceTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private PartyService partyService;

    public PartyServiceTest (){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.setUrl("jdbc:mariadb://200.40.8.10:6033/hyeonworld");
        dataSource.setUsername("hyeonworld1");
        dataSource.setPassword("rkwhr");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Test
    void init() {
    }

    @Test
    void open() {
    }

    @Test
    void getCurrentGameQuery() {
    }

    @Test
    void setCurrentGameStage() {
    }

    @Test
    void getCurrentGameStage() {
    }

    @Test
    void putTarget() {
    }

    @Test
    void getTarget() {

    }
}