package toy.project.auction.feed.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import toy.project.auction.user.domain.User;

import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "FEED")
public class Feed {

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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @JoinColumn(name = "USERID", nullable = false)
  @ManyToOne
  private User userId;

  @Column(name = "CONTENTS")
  private String contents;

  @Column(name = "LIKECOUNT")
  @ColumnDefault("0")
  private Integer likeCount;

  @Column(name = "COMMENTSCOUNT")
  private long commentsCount;

  @Column(name = "VISIBILITY")
  @ColumnDefault("true")
  private Boolean visibility;

  @Column(name = "REGISTERTIME")
  private Date registerTime;

  @Column(name = "UPDATETIME")
  private Date updateTime;

  @Builder
  public Feed(String contents, User userId) {
    this.contents = contents;
    this.userId = userId;
  }


}
