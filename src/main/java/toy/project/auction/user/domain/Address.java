package toy.project.auction.user.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ADDRESS")
public class Address {

  @Id
  @Column(name = "ADDRESSID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JoinColumn(name = "USERID", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private User userId;


  @Column(name = "ADDRESS", nullable = false)
  private String address;

  @Column(name = "DELETED", nullable = false)
  @ColumnDefault("false")
  private boolean deleted;

  @Builder // 빌더 패턴 적용
  public Address(String address, User user) {
    this.address = address;
    this.userId = user;
  }
}
