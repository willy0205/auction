package toy.project.auction.auction.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import toy.project.auction.auction.domain.Auction;
import toy.project.auction.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
  Optional<Long> countBySetter(User setter);
  Optional<List<Auction>> findAllBySetter(Pageable pageable, User setter);
}
