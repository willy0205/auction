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


/**
 * [JPA 연관관계 매핑 팁]
 * 1. 모든 연관관계는 지연로딩(LAZY)으로 설정
 *  - 즉시로딩(EAGER)은 어떤 SQL이 실행되는지 예측이 어렵다.
 *  - JPQL을 사용하면 N + 1 문제가 발생할 가능성이 높다.
 *  - 연관된 엔티티를 함께 DB에서 조회해야 하면, fetch join 또는 엔티티 그래프 기능을 사용한다.
 *  - @XToOne(OneToOne, ManyToOne) 관계는 기본이 즉시로딩이므로 직접 지연로딩으로 설정해야 한다.
 *
 * 2. 컬렉션은 필드에서 바로 초기화
 *  - 컬렉션은 필드에서 바로 초기화 하는 것이 null 문제에서 안전하다.
 *  - 하이버네이트는 엔티티를 영속화 할 때, 컬랙션을 감싸서 하이버네이트가 제공하는 내장 컬렉션으로 변경한다.
 *    - 임의의 메서드에서 컬렉션을 잘못 생성하면 하이버네이트 내부 메커니즘에서 문제가 발생할 수 있다.
 *    - 필드레벨에서 초기화 하는 것이 가장 안전하고 코드도 간결하다.
 *
 * 3. 양방향 매핑의 경우 연관관계 주인을 정해야 한다.
 *  - 두 엔티티중 하나를 연관관계 주인으로 정해야 한다.
 *  - 연관관계 주인만 외래 키를 관리 (등록, 수정)
 *  - 주인이 아닌 쪽은 읽기만 가능
 *  - 주인은 mappedBy 속성 사용 X
 *  - 주인이 아니면 mappedBy 속성으로 주인 지정
 *  - (중요) 외래 키가 있는 곳을 연관관계 주인으로 정한다.
 *    - 연관관계 주인은 비즈니스 상 우위에 있는 것을 선택하는 것이 아니다.
 *    - ex) 자동차와 바퀴
 *    - 자동차가 연관관계 주인이 되는 경우
 *      - 바퀴를 교체하려면 자동차에서 바퀴를 찾아서 바꿔야 한다.
 *    - 바퀴가 연관관계 주인이 되는 경우
 *      - 새로운 바퀴를 끼워넣으면 됨
 *      - 바퀴를 통해 자동차를 바꾸는 일은 없다.
 *      - 자동차를 바꾸고 싶으면 새로운 엔티티 생성하면 되지 않을까? 비즈니스 로직상
 */

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
  @Column(name = "AUCTIONID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JoinColumn(name = "SETTERID", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private User setter;

  @JoinColumn(name = "CATEGORYID", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
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
  @OneToOne(fetch = FetchType.LAZY)
  private Feed feedId;

  @OneToMany(mappedBy = "auction")
  private List<AuctionComment> comments = new ArrayList<>();

  @OneToMany(mappedBy = "auction")
  private List<Bid> bids = new ArrayList<>();

  @OneToMany(mappedBy = "auction")
  private List<AuctionImage> images = new ArrayList<>();

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
