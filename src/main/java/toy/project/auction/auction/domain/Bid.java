package toy.project.auction.auction.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "BID")
public class Bid {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "BIDDERID", nullable = false)
  private Long bidderId;

  @ManyToOne
  @JoinColumn(name = "AUCTIONID", nullable = false)
  private Auction auction;

  @Column(name = "BIDAMOUNT", nullable = false)
  private Long bidAmount;

  @Column(name = "BIDTIME", nullable = false)
  private Date bidTime;

  // Getters and Setters
}