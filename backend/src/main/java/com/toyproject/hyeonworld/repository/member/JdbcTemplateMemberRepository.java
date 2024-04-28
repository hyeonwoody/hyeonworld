package com.toyproject.hyeonworld.repository.member;

import com.toyproject.hyeonworld.DTO.Member.MemberCreateDTO;
import com.toyproject.hyeonworld.DTO.Member.MemberDTO;
import com.toyproject.hyeonworld.entity.Member;

import com.toyproject.hyeonworld.entity.Submission;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class JdbcTemplateMemberRepository {


    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemberRepository (DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public MemberDTO findDTOById(Long id) {
        String sql = "SELECT id, name, party_type, relation FROM member WHERE id = ? LIMIT 1";
        List<MemberDTO> retMember = jdbcTemplate.query(sql, (rs, rowNum) -> new MemberDTO(rs.getLong("id"),
                        rs.getString("name"), rs.getByte("party_type"), rs.getByte("relation"))
                , id);

        if (retMember.isEmpty()) {
            return null;
        } else {
            return retMember.get(0);
        }
    }
    public Member findById(Long logoutId) {
        String sql = "SELECT id, login, in_game FROM member WHERE id = ? LIMIT 1";

        List<Member> retMember = jdbcTemplate.query(sql, (rs, rowNum) -> new Member(rs.getLong("id"),
                        rs.getBoolean("login"), rs.getBoolean("in_game"))
                , logoutId);

        if (retMember.isEmpty()) {
            return null;
        } else {
            return retMember.get(0);
        }
    }

    public Member findByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);

        String sql = "SELECT id,name,login,player FROM member WHERE name = ? LIMIT 1";

        List <Member> retMember = jdbcTemplate.query(sql, (rs, rowNum) -> new Member(rs.getLong("id"),
                            rs.getString("name"),
                            rs.getBoolean("login"),
                            rs.getBoolean("player"))
                            , name);
        if (retMember.isEmpty()){
            return null;
        }
        else {
            return retMember.get(0);
        }
    }



//    public void updateLoginAndInGameColumns() {
//        String sql = "UPDATE Member e SET e.login = false";
//        jdbcTemplate.update (sql);
//    }

//    public List<String> getCorrectNameList(int answer) {
//        return this.findAll().stream()
//                .filter(member -> member.isLogin())
//                .filter (member -> member.getAnswer() == answer)
//                .map(Member::getName)
//                .collect(Collectors.toList());
//    };

//    public Collection<Member> findAll() {
//        String sql = "SELECT* FROM Member";
//        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
//        // Map the results to Member objects (manual mapping)
//        Collection<Member> members = new ArrayList<>();
//        for (Map<String, Object> row : rows) {
//            Member member = new Member(
//            );
//            members.add(member);
//        }
//        return members;
//    }


    public void saveAll(List<Member> members) {

        List<Map<String, Object>> params = new ArrayList<>();
        for (Member member : members){
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("name", member.getName());
            paramMap.put("email", member.getEmail());
            params.add(paramMap);
        }
    }
    public Long create(MemberCreateDTO member) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO member (name, party_type, relation) VALUE (?, ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, member.getName());
            ps.setInt(2, member.getPartyType());
            ps.setInt(3, member.getRelation());
            return ps;
        }, keyHolder);
        Number ret = keyHolder.getKey().longValue();
        return (Long) ret;
    }

    public Long edit(MemberCreateDTO member) {
        String sql = "UPDATE member SET party_type = ?, relation = ? WHERE name = ?";
        jdbcTemplate.update(sql, new Object[]{member.getPartyType(), member.getRelation(), member.getName()});
        return 0L;
    }

    public void delete(Long id) {
        String sql = "DELETE FROM member WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void toggleLogin(Member member) {
        String sql = "UPDATE member SET login = ? WHERE id = ?";
        jdbcTemplate.update(sql, !member.getLogin() ,member.getId());
    }

    public void init() {
        String sql = "UPDATE member SET login = false, in_game = false WHERE 1=1";
        jdbcTemplate.update(sql);
    }

    public void toggleinGame(Member member) {
        String sql = "UPDATE member SET in_game = ? WHERE id = ?";
        jdbcTemplate.update(sql, !member.getInGame() ,member.getId());
    }

    public List<Member> findLoggedInButNotInGameMembers() {
        String sql = "SELECT name FROM member where login = true AND in_game = false";

            return jdbcTemplate.query(sql, (rs, rowNum) -> new Member(rs.getString("name")));
    }

    public void setAnswer(Member member) {
        String sql = "UPDATE member SET answer = ? WHERE id = ?";
        jdbcTemplate.update(sql, member.getAnswer(), member.getId());
    }

    public List<Member> getTopScoringParticipants() {
        String sql = "SELECT * FROM member WHERE login = true ORDER BY total_score DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Member(rs.getString("name"), rs.getLong("total_score")));
    }



    public List<Member> getCorrectMembers(Integer answer) {
        String sql = "SELECT * FROM member WHERE answer = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Member(rs.getString("name"), rs.getLong("total_score")), answer);
    }

    public void updateScore(List<Member> correctMembers, Long correctScore, Long wrongScore) {
        StringBuilder sql = new StringBuilder("UPDATE member SET total_score = total_score + (CASE WHEN login = true THEN ? ELSE ? END) ");

        sql.append("WHERE id IN (");

        // Add placeholders for member IDs
        List<Long> memberIds = correctMembers.stream().map(Member::getId).collect(Collectors.toList());
        for (int i = 0; i < memberIds.size(); i++) {
            sql.append("?");
            if (i < memberIds.size() - 1) {
                sql.append(",");
            }
        }
        sql.append(")");

        List<Object> params = new ArrayList<>();
        params.add(correctScore); // Points for correct answers (applicable only to logged-in members)
        if (wrongScore != null && wrongScore > 0) {
            params.add(wrongScore); // Points deducted for wrong answers (optional)
        }
        params.addAll(memberIds); // List of member IDs

        jdbcTemplate.update (sql.toString(), params.toArray());
    }


    public List<String> getCorrectMemberNames(int answer) {
        String sql = "SELECT name FROM member WHERE answer = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
            rs.getString("name"), answer);
    }

    public Submission findByNameGetSubmission(String memberName) {
        String sql = "SELECT s.* FROM submission s INNER JOIN member m ON m.name = ? WHERE s.member_id = m.id ORDER BY s.created_at DESC LIMIT 1;";
        sql = "SELECT s.* FROM submission s INNER JOIN member m ON m.name = ? WHERE s.member_id = m.id ORDER BY s.created_at DESC LIMIT 1";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            return new Submission(rs.getString("number"));}, memberName);
    }




    public List<Member> getAllLoginMembersSubmission() {
        String sql = "SELECT m.name AS memberName, s.* AS submissionTable FROM member m INNER JOIN submission s ON m.id = s.member_id;";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Member(rs.getString("memberName"), (List<Submission>) rs.getArray("submissionTable")));
    }

    public List<Member> getLoginMemberIds() {
        String sql = "SELECT id FROM member WHERE login = true;";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Member(rs.getString("id")));
    }




//    public void save(Member member) {
//        String sql;
//        if (member.getId() != null) {
//            sql = "UPDATE Member SET name = ? WHERE id = ?";
//        } else {
//            sql = "INSERT INTO Member (name) VALUES (?, ?)";
//        }
//        List<Object> params = new ArrayList<>();
//        params.add(member.getName());
//        if (member.getId() != null) {
//            params.add(member.getId());
//        }
//
//        jdbcTemplate.update(sql, params);
//    }
//    public Optional<Member> findById(Long logoutId) {
//        String sql = "SELECT * FROM Member WHERE id = ?";
//        RowMapper<Optional<Member>> rowMapper = (rs, rowNum) -> null;
//        return jdbcTemplate.queryForObject(sql, rowMapper);
//    }
}
