package toy.project.auction.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.project.auction.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {



}
