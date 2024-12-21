package toy.project.auction.auction.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FeedId implements Serializable {
  private Long userId;
  private Long id;

  public FeedId() {}

  public FeedId(Long userId, Long id) {
    this.userId = userId;
    this.id = id;
  }

  // equals() 및 hashCode() 메소드 구현
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof FeedId)) return false;
    FeedId feedId = (FeedId) o;
    return Objects.equals(userId, feedId.userId) && Objects.equals(id, feedId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, id);
  }

  // Getters 및 Setters
}
