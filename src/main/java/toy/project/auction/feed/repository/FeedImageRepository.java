package toy.project.auction.feed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.project.auction.feed.domain.FeedImage;

public interface FeedImageRepository extends JpaRepository<FeedImage, Long> {
}
