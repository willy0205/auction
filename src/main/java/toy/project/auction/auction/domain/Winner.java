package toy.project.auction.auction.domain;

import jakarta.persistence.*;
import toy.project.auction.auction.enums.WinnerStatus;
import toy.project.auction.user.domain.User;

import java.util.Date;

@Entity
@Table(name = "WINNERS")
public class Winner {
  @Id
  @Column(name = "WINNERSID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "AUCTIONID", nullable = false)
  private Auction auction;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "BIDID", nullable = false)
  private Bid bid;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "WINNERID")
  private User winnerId;

  @Column(name = "FINALPRICE", nullable = false)
  private Long finalPrice;

  @Column(name = "BIBTIME", nullable = false)
  private Date bidTime;

  @Enumerated(EnumType.STRING)
  @Column(name = "STATUS", nullable = false)
  private WinnerStatus status;

  // Getters and Setters
}
