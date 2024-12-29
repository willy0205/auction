package toy.project.auction.auction.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "AUCTION_COMMENTS")
public class AuctionComment {
  @Id
  @Column(name = "AUCTION_COMMENTSID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "AUCTIONID", nullable = false)
  private Auction auction;

  @Column(name = "USERID", nullable = false) //todo 코멘트랑 유저 다대다 관계 : 한 유저가 코멘트를 여러 개 달 수 있고, 여러 유저가 코멘트를 달 수 있음 -> 중간에 테이블 하나 둬서 다대다 관계 풀어야할듯
  private Long userId;


  @Column(name = "CONTENT", nullable = false)
  private String content;

  @Column(name = "REGISTERTIME", nullable = false)
  private Date registerTime;

  @Column(name = "UPDATETIME")
  private Date updateTime;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PARENTID")
  private AuctionComment parent;

  @OneToMany(mappedBy = "parent")
  private List<AuctionComment> child;

  // Getters and Setters

  // == 연관관계 편의 메서드 ==//
  public void addChildAuctionComment(AuctionComment child) {
    this.child.add(child);
    child.setParent(parent);
  }
}