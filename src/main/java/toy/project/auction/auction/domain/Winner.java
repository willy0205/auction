package toy.project.auction.auction.domain;

import jakarta.persistence.*;
import toy.project.auction.auction.enums.WinnerStatus;

import java.util.Date;

@Entity
@Table(name = "WINNERS")
public class Winner {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "AUCTIONID", nullable = false)
  private Auction auction;

  @ManyToOne
  @JoinColumn(name = "BIDID", nullable = false)
  private Bid bid;

  @Column(name = "WINNERID", nullable = false)
  private Long winnerId;

  @Column(name = "FINALPRICE", nullable = false)
  private Long finalPrice;

  @Column(name = "BIBTIME", nullable = false)
  private Date bidTime;

  @Enumerated(EnumType.STRING)
  @Column(name = "STATUS", nullable = false)
  private WinnerStatus status;

  // Getters and Setters
}
