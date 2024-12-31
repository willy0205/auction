package toy.project.auction.feed.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import toy.project.auction.feed.domain.Feed;
import toy.project.auction.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface FeedRepository extends JpaRepository<Feed, Long> {

  Optional<List<Feed>> findAllByUserIdAndVisibility(User user, Pageable pageable, boolean isVisibility);


}
