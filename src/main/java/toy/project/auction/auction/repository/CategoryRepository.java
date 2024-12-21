package toy.project.auction.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.project.auction.auction.domain.Categroy;

public interface CategoryRepository extends JpaRepository<Categroy, Long> {
}
