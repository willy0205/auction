package toy.project.auction.user.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import toy.project.auction.user.domain.User;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class UserDataInitializer implements CommandLineRunner {

  private final UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {
    // 데이터가 없을 경우만 초기화
    if (userRepository.count() == 0) {
      User user1 = User.builder()
          .username("testuser1")
          .password("password1")
          .email("testuser1@example.com")
          .build();

      User user2 = User.builder()
          .username("testuser2")
          .password("password2")
          .email("testuser2@example.com")
          .build();

      userRepository.saveAll(Arrays.asList(user1, user2));

      System.out.println("Test users created.");
    }
  }

}
