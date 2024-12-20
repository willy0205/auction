package toy.project.auction.auction.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import toy.project.auction.auction.enums.ImageType;

@Entity
@Getter
@Setter
@Table(name = "FEEDIMAGE")
public class FeedImage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

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
  private Integer fileSize;

  @Enumerated(EnumType.STRING)
  @Column(name = "IMAGETYPE", nullable = false)
  private ImageType imageType;

  @Column(name = "USERID", nullable = false)
  private Long userId;

  // Getters and Setters
}
