package toy.project.auction.auction.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import toy.project.auction.user.domain.User;

import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "FEED")
public class Feed {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JoinColumn(name = "USERID", nullable = false)
  @ManyToOne
  private User userId;

  @Column(name = "CONTENTS")
  private String contents;

  @Column(name = "LIKECOUNT")
  private Integer likeCount;

  @Column(name = "COMMENTSCOUNT")
  private Long commentsCount;

  @Column(name = "VISIBILITY")
  private Boolean visibility;

  @Column(name = "REGISTERTIME")
  private Date registerTime;

  @Column(name = "UPDATETIME")
  private Date updateTime;

  // Getters and Setters
}
