package toy.project.auction.auction.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.project.auction.auction.enums.ImageType;
import toy.project.auction.user.domain.User;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "AUCTIONIMAGE")
public class AuctionImage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "AUCTIONID", nullable = false)
  private Auction auction;

  @Column(name = "ORIGINALFILENAME")
  private String originalFileName;

  @Column(name = "SAVINGFILENAME")
  private String savingFileName;

  @Column(name = "IMAGEURL")
  private String imageUrl;

  @Column(name = "FILESIZE")
  private long fileSize;

  @Enumerated(EnumType.STRING)
  @Column(name = "IMAGETYPE")
  private ImageType imageType;

  @JoinColumn(name = "USERID", nullable = false)
  @ManyToOne
  private User userId;

  @Builder
  public AuctionImage(Auction auction, String originalFileName, String savingFileName, String imageUrl, long fileSize, ImageType imageType, User setter) {
    this.auction = auction;
    this.originalFileName = originalFileName;
    this.savingFileName = savingFileName;
    this.imageUrl = imageUrl;
    this.fileSize = fileSize;
    this.imageType = imageType;
    this.userId = setter;
  }
}