package toy.project.auction.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.project.auction.auction.domain.Auction;
import toy.project.auction.auction.domain.AuctionImage;
import toy.project.auction.auction.enums.ImageType;

import java.util.List;
import java.util.Optional;

public interface AuctionImageRepository extends JpaRepository<AuctionImage, Long> {
  Optional<AuctionImage> findByAuctionAndImageType(Auction auction, ImageType imageType);
  Optional<List<AuctionImage>> findAllByAuctionAndImageType(Auction auction, ImageType imageType);
}
