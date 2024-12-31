package toy.project.auction.user.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.project.auction.auction.domain.Auction;
import toy.project.auction.auction.domain.Bid;
import toy.project.auction.feed.domain.Feed;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
@Getter
@NoArgsConstructor // 기본 생성자 추가
public class User {

  @Id
  @Column(name = "USERSID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "USERNAME", nullable = false)
  private String username;

  @Column(name = "PASSWORD", nullable = false)
  private String password;

  @Column(name = "EMAIL", nullable = false)
  private String email;

  @OneToMany(mappedBy = "setter")
  private List<Auction> auctions = new ArrayList<>();

  @OneToMany(mappedBy = "userId")
  private List<Feed> feeds = new ArrayList<>();

  @Builder // 빌더 패턴 적용
  public User(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }
}
