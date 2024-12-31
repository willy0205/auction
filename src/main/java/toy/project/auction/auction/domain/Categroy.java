package toy.project.auction.auction.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor // 기본 생성자 추가
@Table(name = "CATEGORY")
public class Categroy {

  @PrePersist
  public void prePersist() {
    this.registerTime = new Date(); // 현재 시간으로 설정
    this.updateTime = new Date();   // 현재 시간으로 설정
  }

  @PreUpdate
  public void preUpdate() {
    this.updateTime = new Date();   // 현재 시간으로 설정
  }

  @Id
  @Column(name = "CATEGORYID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "DESCRIPPTION")
  private String description;

  @Column(name = "REGISTERTIME")
  private Date registerTime;

  @Column(name = "UPDATETIME")
  private Date updateTime;

  @Column(name = "DELETED")
  @ColumnDefault("false")
  private boolean deleted;

  @OneToMany(mappedBy = "categoryId")
  private List<Auction> auctions = new ArrayList<>();

  @Builder // 빌더 패턴 적용
  public Categroy(String name, String description){
    this.name = name;
    this.description = description;
  }

}
