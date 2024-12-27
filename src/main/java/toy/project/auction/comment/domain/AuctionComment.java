package toy.project.auction.comment.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import toy.project.auction.auction.domain.Auction;

import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "AUCTION_COMMENTS")
public class AuctionComment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "AUCTIONID", nullable = false)
  private Auction auction;

  @Column(name = "USERID", nullable = false)
  private Long userId;

  @JoinColumn(name = "PARENTID")
  @ManyToOne(fetch = FetchType.LAZY)
  private AuctionComment parentId;

  @OneToMany(mappedBy = "parentId", cascade = CascadeType.ALL)
  private List<AuctionComment> replies = new ArrayList<>();

  @Column(name = "CONTENT", nullable = false)
  private String content;

  @Column(name = "REGISTERTIME", nullable = false)
  private Date registerTime;

  @Column(name = "UPDATETIME")
  private Date updateTime;
}