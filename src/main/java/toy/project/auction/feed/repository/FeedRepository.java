package toy.project.auction.feed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.project.auction.feed.domain.Feed;

public interface FeedRepository extends JpaRepository<Feed, Long> {



}
