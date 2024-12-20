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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "AUCTIONID", nullable = false)
  private Auction auction;

  @Column(name = "USERID", nullable = false)
  private Long userId;

  @Column(name = "PARENTID")
  private Long parentId;

  @Column(name = "CONTENT", nullable = false)
  private String content;

  @Column(name = "REGISTERTIME", nullable = false)
  private Date registerTime;

  @Column(name = "UPDATETIME")
  private Date updateTime;

  // Getters and Setters
}