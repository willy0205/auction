package toy.project.auction.feed.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import toy.project.auction.auction.enums.ImageType;
import toy.project.auction.user.domain.User;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "FEEDIMAGE")
public class FeedImage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

//  @EmbeddedId
//  private FeedId feedId; // 복합 키 필드

  @ManyToOne
  @JoinColumn(name = "FEEDID", nullable = false)
  private Feed feed;

  @Column(name = "ORIGINALFILENAME", nullable = false)
  private String originalFileName;

  @Column(name = "SAVINGFILENAME", nullable = false)
  private String savingFileName;

  @Column(name = "IMAGEURL", nullable = false)
  private String imageUrl;

  @Column(name = "FILESIZE")
  private long fileSize;

  @Enumerated(EnumType.STRING)
  @Column(name = "IMAGETYPE", nullable = false)
  private ImageType imageType;

  @Builder
  public FeedImage(Feed feed, String originalFileName, String savingFileName, String imageUrl, long fileSize, ImageType imageType) {
    this.feed = feed;
    this.savingFileName = savingFileName;
    this.imageUrl = imageUrl;
    this.fileSize = fileSize;
    this.imageType = imageType;
    this.originalFileName = originalFileName;
  }
}
