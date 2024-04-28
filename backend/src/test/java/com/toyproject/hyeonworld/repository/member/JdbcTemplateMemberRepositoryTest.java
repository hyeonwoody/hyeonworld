package com.toyproject.hyeonworld.repository.member;

import com.toyproject.hyeonworld.DTO.Member.MemberCreateDTO;
import com.toyproject.hyeonworld.DTO.Member.MemberDTO;
import com.toyproject.hyeonworld.entity.Member;
import com.toyproject.hyeonworld.entity.Submission;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JdbcTemplateMemberRepositoryTest {
    private JdbcTemplate jdbcTemplate;
    public JdbcTemplateMemberRepositoryTest(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.setUrl("jdbc:mariadb://200.40.8.10:6033/hyeonworld");
        dataSource.setUsername("hyeonworld1");
        dataSource.setPassword("rkwhr");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Test
    void findByNameGetSubmission() {
        String memberName = "정현우";
        String sql = "SELECT s.*\n" +
                "FROM submission s\n" +
                "INNER JOIN member m ON s.member_id = m.id\n" +
                "WHERE m.name = ?\n" +
                "ORDER BY s.created_at DESC\n" +
                "LIMIT 1;";
        sql = "SELECT s.* FROM submission s INNER JOIN member m ON m.name = ? WHERE s.member_id = m.id LIMIT 1";
        sql = "SELECT s.* FROM submission s INNER JOIN member m ON m.name = ? WHERE s.member_id = m.id ORDER BY s.created_at DESC LIMIT 1";




        Submission s = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            System.out.println(rs.getString("text"));
            return new Submission(rs.getString("text"));}, memberName);
        assertNotNull(s);
    }

    @Test
    void getAllLoginMembersSubmission() {

        String sql = "SELECT m.name AS memberName, (SELECT * FROM submission WHERE submission.member_id = m.id LIMIT 1) AS latestSubmission FROM member m WHERE m.login = true;";
        List <Member> members = jdbcTemplate.query(sql, (rs, rowNum) -> {
            System.out.println(rs.getObject("latestSubmission"));
            return new Member(rs.getString("memberName"));});

        for (Member m : members){
            System.out.println(m.getName());
        }
    }

    @Test
    void getLoginMemberIds() {
        String sql = "SELECT id FROM member WHERE login = true;";
        List <Member> members = jdbcTemplate.query(sql, (rs, rowNum) -> new Member(rs.getLong("id")));
        for (Member member :members){
            System.out.println(member.getId());
        }
    }

    @Test
    void create() {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MemberCreateDTO member = new MemberCreateDTO();
        member.setName("장철수");
        member.setPartyType((byte) 8);
        member.setRelation((byte) 3);
        String sql = "INSERT INTO member (name, party_type, relation) VALUE (?, ?, ?)";
        Member memberRet = new Member();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, member.getName());
            ps.setInt(2, member.getPartyType());
            ps.setInt(3, member.getRelation());
            return ps;
        }, keyHolder);
        Number a = keyHolder.getKey();
        int ab = 0;
    }

    @Test
    void edit() {
        MemberCreateDTO member = new MemberCreateDTO();
        member.setName("모르겠ㅁ");
        member.setPartyType((byte) 8);
        member.setRelation((byte) 3);
        String sql = "UPDATE member SET party_type = ?, relation = ? where name = ?";
        jdbcTemplate.update(sql, new Object[]{member.getPartyType(), member.getRelation(), member.getName()});
    }


    @Test
    void delete() {
        Long id = 1222L;
        String sql = "DELETE FROM member WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Test
    void findDTOById() {
        Long id = 3L;
        String sql = "SELECT id, name, party_type, relation FROM member WHERE id = ? LIMIT 1";
        List<MemberDTO> retMember = jdbcTemplate.query(sql, (rs, rowNum) -> new MemberDTO(rs.getLong("id"),
                        rs.getString("name"), rs.getByte("party_type"), rs.getByte("relation"))
                , id);

        if (retMember.isEmpty()) {

        } else {

        }
    }
}