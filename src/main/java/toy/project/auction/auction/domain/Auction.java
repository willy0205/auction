package toy.project.auction.auction.domain;

import jakarta.persistence.*;
import java.util.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import toy.project.auction.auction.enums.AuctionStatus;
import toy.project.auction.auction.enums.CurrencyUnit;
import toy.project.auction.auction.model.AuctionRequest;
import toy.project.auction.comment.domain.AuctionComment;
import toy.project.auction.feed.domain.Feed;
import toy.project.auction.user.domain.User;

@Entity
@Getter
@Setter
@Table(name = "AUCTION")
public class Auction {

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

  @JoinColumn(name = "SETTERID", nullable = false)
  @ManyToOne
  private User setter;

  @JoinColumn(name = "CATEGORYID", nullable = false)
  @ManyToOne
  private Categroy categoryId;

  @Column(name = "TITLE", nullable = false)
  private String title;

  @Column(name = "SUMMARY")
  private String summary;

  @Column(name = "DESCRIPTION", nullable = false)
  private String description;

  @Column(name = "STARTINGPRICE", nullable = false)
  private long startingPrice;

  @Column(name = "CURRENTPRICE", nullable = false)
  private long currentPrice;

  @Enumerated(EnumType.STRING)
  @Column(name = "CURRENCYUNIT")
  private CurrencyUnit currencyUnit;

  @Column(name = "REGISTERTIME", nullable = false)
  private Date registerTime;

  @Column(name = "UPDATETIME", nullable = false)
  private Date updateTime;

  @Column(name = "STARTTIME", nullable = false)
  private Date startTime;

  @Column(name = "ENDTIME", nullable = false)
  private Date endTime;

  @Enumerated(EnumType.STRING)
  @Column(name = "STATUS", nullable = false)
  private AuctionStatus status;

  @Column(name = "BIBINCREMENT")
  private Integer bidIncrement;

  @Column(name = "DELETED", nullable = false)
  @ColumnDefault("false")
  private boolean deleted;

  @JoinColumn(name = "FEEDID")
  @OneToOne
  private Feed feedId;

  @OneToMany(mappedBy = "auction")
  private List<AuctionComment> comments;

  @OneToMany
  private List<Bid> bids;

  @OneToMany(mappedBy = "auction")
  private List<AuctionImage> images;

  public static Auction fromRequest(AuctionRequest request, User setter, Categroy categroy) {
    Auction auction = new Auction();
    auction.setSetter(setter);
    auction.setTitle(request.getTitle());
    auction.setSummary(request.getSummary());
    auction.setDescription(request.getDescription());
    auction.setStartingPrice(request.getStartingPrice());
    auction.setCurrentPrice(request.getStartingPrice());
    auction.setCurrencyUnit(request.getCurrencyUnit());
    auction.setStartTime(request.getStartTime());
    auction.setEndTime(request.getEndTime());
    auction.setStatus(request.getStatus());
    auction.setBidIncrement(request.getBidIncrement());
    auction.setCategoryId(categroy);
    // thumbnailImage와 imageList는 추가적인 처리 필요
    return auction;
  }

}
