package toy.project.auction.user.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@Getter
@NoArgsConstructor // 기본 생성자 추가
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "USERNAME", nullable = false)
  private String username;

  @Column(name = "PASSWORD", nullable = false)
  private String password;

  @Column(name = "EMAIL", nullable = false)
  private String email;

  @Builder // 빌더 패턴 적용
  public User(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }
}
