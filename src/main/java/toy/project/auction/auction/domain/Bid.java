package toy.project.auction.auction.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import toy.project.auction.user.domain.User;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "BID")
public class Bid {
  @Id
  @Column(name = "BIDID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JoinColumn(name = "BIDDERID", nullable = false)
  @OneToOne(fetch = FetchType.LAZY)
  private User bidderId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "AUCTIONID", nullable = false)
  private Auction auction;

  @Column(name = "BIDAMOUNT", nullable = false)
  private Long bidAmount;

  @Column(name = "BIDTIME", nullable = false)
  private Date bidTime;

  // Getters and Setters
}