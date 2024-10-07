package com.toyproject.hyeonworld.api.user.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import com.toyproject.hyeonworld.api.score.infarstructure.entity.Score;
import com.toyproject.hyeonworld.api.user.domain.dto.in.CreateUserCommand;
import com.toyproject.hyeonworld.api.user.domain.dto.in.RetrieveUserWaitingListCommand;
import com.toyproject.hyeonworld.api.user.domain.dto.in.UpdateUserCommand;
import com.toyproject.hyeonworld.api.user.domain.dto.out.UserInGameInfo;
import com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfo;
import com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfos;
import com.toyproject.hyeonworld.api.user.domain.dto.out.UserWaitingListInfo;
import com.toyproject.hyeonworld.api.user.infrastructure.UserRepository;
import com.toyproject.hyeonworld.api.user.infrastructure.entity.User;
import com.toyproject.hyeonworld.api.user.infrastructure.jpa.UserJpaRepository.UserNameProjection;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 10. 7.
 */
class UserServiceTest {
  private final UserRepository userRepository = Mockito.mock(UserRepository.class);
  private final UserService service = new UserService(userRepository);

  private long partyId;
  private long roundId;
  private long submissionId;
  private String name;
  private byte relationType;
  private byte relation;
  @BeforeEach
  void setUp() {
    partyId = 4L;
    roundId = 20L;
    submissionId = 58L;
    name = "James Lee";
    relationType = 1;
    relation = 2;
  }

  @Test
  void createUser() {
    CreateUserCommand command = new CreateUserCommand(name, relationType, relation);

    User mockUser = User.builder()
            .name(name)
                .relationType(relationType)
                    .relation(relation)
                        .build();

    when(userRepository.save(any(User.class))).thenReturn(mockUser);

    UserInfo result = service.createUser(command);
    assertNotNull(result);
    assertEquals(name, result.getName());
    assertEquals(relationType, result.getRelationType());
    assertEquals(relation, result.getRelation());
    verify(userRepository, times(1)).save(any(User.class));
  }

  @Test
  void getAllUsers() {

    List<User> mockUsers = Arrays.asList(
        User.builder()
            .name(name)
            .relationType(relationType)
            .relation(relation)
            .build(),
        User.builder()
            .name("Hyeonwoo")
            .relationType(relationType)
            .relation(relation)
            .build()
    );

    when(userRepository.findAll()).thenReturn(mockUsers);

    UserInfos result = service.getAllUsers();
    assertNotNull(result);
    assertEquals(mockUsers.size(), result.size());
    for (int i = 0; i < result.size(); ++i){
      User user = mockUsers.get(i);
      UserInfo info = result.get(i);
      assertEquals(user.getName(), info.getName());
      assertEquals(user.getRelationType(), info.getRelationType());
      assertEquals(user.getRelation(), info.getRelation());
    }
  }

  @Test
  void getUserById() {
    long userId = 2L;
    User mockUser = User.builder()
        .id(userId)
        .build();
    when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

    UserInfo result = service.getUserById(userId);
    assertNotNull(result);
    assertEquals(mockUser.getId(), result.getId());
  }

  @Test
  void updateUser() {
    long userId = 2L;
    UpdateUserCommand command = new UpdateUserCommand(userId, Optional.ofNullable(name),
        Optional.ofNullable(relationType), Optional.ofNullable(relation));
    User mockUser = User.builder()
        .id(userId)
        .build();
    User updatedUser = User.builder()
            .id(userId)
                .relationType(relationType)
                    .relation(relation)
                        .build();
    UserInfo expectedUser = UserInfo.from(updatedUser);


    when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
    when(userRepository.save(any(User.class))).thenReturn(updatedUser);

    UserInfo result = service.updateUser(command);
    assertNotNull(result);
    assertEquals(userId, result.getId());
    assertEquals(relationType, result.getRelationType());
    assertEquals(relation, result.getRelation());
    verify(userRepository, times(1)).findById(userId);
    verify(userRepository, times(1)).save(any(User.class));
  }

  @Test
  void deleteUser() {
    long userId = 1L;
    User deletedUser = User.builder()
            .id(1L)
                .name(name)
                    .relationType(relationType)
                        .relation(relation)
                            .build();
    when(userRepository.deleteById(userId)).thenReturn(1);

    UserInfo result = service.deleteUser(userId);
    assertNotNull(result);
    assertInstanceOf(UserInfo.class, result);
    verify(userRepository, times(1)).deleteById(userId);
  }

  @Test
  void initUsers() {
    List<User> loggedInUsers = Arrays.asList(
        User.builder()
            .id(1L)
            .relationType(relationType)
            .relation(relation)
            .build(),
        User.builder()
            .id(2L)
            .relationType(relationType)
            .relation(relation)
            .build(),
        User.builder()
            .id(3L)
            .relationType(relationType)
            .relation(relation)
            .build()
    );
    when(userRepository.findByLogin(true)).thenReturn(loggedInUsers);
    ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
    when(userRepository.save(any(User.class))).thenAnswer(invocation->
        invocation.getArgument(0));

    int result = service.initUsers();

    assertEquals(loggedInUsers.size(), result);
    verify(userRepository, times(1)).findByLogin(true);
    verify(userRepository, times(3)).save(userCaptor.capture());

    List<User> savedUsers = userCaptor.getAllValues();
    for (User savedUser : savedUsers){
      assertFalse(savedUser.getLogin());
    }
  }

  @Test
  void getUserByName() {
    String name = "Charlie";
    User mockUser = User.builder()
        .name(name)
        .build();
    when(userRepository.findByName(name)).thenReturn(Optional.of(mockUser));

    UserInfo result = service.getUserByName(name);
    assertNotNull(result);
    assertEquals(name, result.getName());
  }

  @Test
  void confirmLogin() {
    String name = "testUser";
    User mockUser = User.builder()
                .name(name)
                .build();
    User updatedUser = User.builder()
        .name(name)
        .login(true)
        .inGame(false)
        .build();

    when(userRepository.findByName(name)).thenReturn(Optional.of(mockUser));
    when(userRepository.save(any(User.class))).thenReturn(updatedUser);

    UserInfo result = service.confirmLogin(name);
    assertNotNull(result);
    assertTrue(result.isLogin());
    assertFalse(result.isInGame());
  }

  @Test
  void confirmLogOut() {
    long userId = 2L;
    String name = "testUser";
    User mockUser = User.builder()
        .id(userId)
        .name(name)
        .build();
    User updatedUser = User.builder()
        .id(userId)
        .name(name)
        .login(false)
        .inGame(false)
        .build();

    when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
    when(userRepository.save(any(User.class))).thenReturn(updatedUser);

    UserInfo result = service.confirmLogOut(userId);
    assertNotNull(result);
    assertFalse(result.isLogin());
    assertFalse(result.isInGame());
  }

  @Test
  void confirmEnterGame() {
    long userId = 2L;
    String name = "testUser";
    User mockUser = User.builder()
        .id(userId)
        .name(name)
        .build();
    User updatedUser = User.builder()
        .id(userId)
        .name(name)
        .login(true)
        .inGame(true)
        .build();

    when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
    when(userRepository.save(any(User.class))).thenReturn(updatedUser);

    UserInfo result = service.confirmLogOut(userId);
    assertNotNull(result);
    assertTrue(result.isLogin());
    assertTrue(result.isInGame());
  }

  @Test
  void confirmExitGame() {
    long userId = 2L;
    String name = "testUser";
    User mockUser = User.builder()
        .id(userId)
        .name(name)
        .build();
    User updatedUser = User.builder()
        .id(userId)
        .name(name)
        .login(true)
        .inGame(false)
        .build();

    when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
    when(userRepository.save(any(User.class))).thenReturn(updatedUser);

    UserInfo result = service.confirmLogOut(userId);
    assertNotNull(result);
    assertTrue(result.isLogin());
    assertFalse(result.isInGame());
  }

  @Test
  void retrieveWaitingList() {
    long userId = 1L;
    RetrieveUserWaitingListCommand command = new RetrieveUserWaitingListCommand(partyId);
    List<User> loggedInUsers = Arrays.asList(
        User.builder()
            .id(userId)
            .name(name)
            .login(true)
            .inGame(false)
            .build(),
        User.builder()
            .id(userId + 1)
            .name("name1")
            .login(true)
            .inGame(true)
            .build(),
        User.builder()
            .id(userId + 1)
            .name("name2")
            .login(true)
            .inGame(false)
            .build()
    );

    when(userRepository.findByLogin(true)).thenReturn(loggedInUsers);

    // Act
    UserWaitingListInfo result = service.retrieveWaitingList(command);

    // Assert
    assertNotNull(result);
    for (int i = 0; i < result.getNames().size(); ++i){
      User loggedInUser = i == 1 ? loggedInUsers.get(i+1) : loggedInUsers.get(i);
      assertEquals(loggedInUser.getName(), result.getNames().get(i));
    }
    verify(userRepository, times(1)).findByLogin(true);
  }

  @Test
  void getNameById() {
    long userId = 1L;
    User mockUser = User.builder()
        .id(userId)
        .name(name)
        .build();
    when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

    String result = service.getNameById(userId);
    assertNotNull(result);
    assertEquals(mockUser.getName(), result);
    verify(userRepository, times(1)).findById(userId);
  }

  @Test
  void getNamesByIds() {
    Set<Long> userIds = Set.of(1L, 2L, 3L);
    List<UserNameProjection> projections = Arrays.asList(
        createUserNameProjection(1L, "first"),
        createUserNameProjection(2L, "second"),
        createUserNameProjection(3L, "third")
    );
    when(userRepository.findNamesByIds(userIds)).thenReturn(projections);

    Map<Long, String> result = service.getNamesByIds(userIds);
    assertNotNull(result);
    assertEquals(userIds.size(), result.size());
    assertEquals(userIds, result.keySet());
    verify(userRepository, times(1)).findNamesByIds(userIds);
  }

  private UserNameProjection createUserNameProjection(Long id, String name){
    return new UserNameProjection() {
      @Override
      public Long getId() {
        return id;
      }

      @Override
      public String getName() {
        return name;
      }
    };
  }
}