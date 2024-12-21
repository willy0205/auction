package toy.project.auction.feed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.project.auction.feed.domain.Feed;
import toy.project.auction.feed.domain.FeedImage;

import java.util.List;
import java.util.Optional;

public interface FeedImageRepository extends JpaRepository<FeedImage, Long> {
  Optional<List<FeedImage>> findAllByFeed(Feed feed);
}
