package toy.project.auction.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.project.auction.comment.domain.AuctionComment;

public interface AuctionCommentRepository extends JpaRepository<AuctionComment, Long> {
}
