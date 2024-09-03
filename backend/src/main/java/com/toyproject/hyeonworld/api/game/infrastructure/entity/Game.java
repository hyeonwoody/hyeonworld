package com.toyproject.hyeonworld.api.game.infrastructure.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Game {
    @Id
    private Long id;
    private String name;
    private String description;
    private boolean playable;

    public static GameBuilder defaultBuilder(){
        return Game.builder();
    }

    public static Game createToDisplay (ResultSet rs) throws SQLException {
        return defaultBuilder()
            .name(rs.getString("name"))
            .description(rs.getString("description"))
            .build();
    }
}
