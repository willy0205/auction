package toy.project.auction.comment.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "FEED_COMMENTS")
public class FeedComment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

}
