package com.toyproject.hyeonworld.api.party.infrastructure.entity;

import com.toyproject.hyeonworld.api.party.application.port.in.BeginPartyCommand;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 5.
*/
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Party {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "relation_type")
  private Byte relationType;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "terminated_at")
  private LocalDateTime terminatedAt;

  @PrePersist
  protected void onCreate(){
    this.createdAt = LocalDateTime.now();
  }

  private static PartyBuilder defaultBuilder(){
    return Party.builder();
  }

  public static Party create (BeginPartyCommand command){
    return defaultBuilder()
        .relationType(command.relationType())
        .build();
  }
}