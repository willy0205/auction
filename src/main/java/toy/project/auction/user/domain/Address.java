package toy.project.auction.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @JoinColumn(name = "USERID", nullable = false)
  @ManyToOne
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
