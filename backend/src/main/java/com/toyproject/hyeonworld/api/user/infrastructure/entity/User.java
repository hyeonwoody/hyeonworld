package com.toyproject.hyeonworld.api.user.infrastructure.entity;

import com.toyproject.hyeonworld.api.user.domain.dto.in.CreateUserCommand;
import com.toyproject.hyeonworld.api.user.domain.dto.in.EditUserCommand;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String email;
  private Boolean login;
  private Byte relationType; //0 : 식구, 1 : 친가, 2 : 외가
  private String name;
  private boolean proposition; //0 : 은 이, 1 : 는 가
  private String nickname;
  private Boolean nicknameProposition;
  private Boolean inGame;
  private Byte relation;
  private Boolean player;

  @PrePersist
  protected void onCreate(){
    login = false;
    inGame = false;
    //proposition settings
  }

  private static UserBuilder defaultBuilder(){
    return User.builder();
  }

  public static User create (CreateUserCommand command){
    return defaultBuilder()
        .name(command.name())
        .relationType(command.relationType())
        .relation(command.relation())
        .build();
  }
}