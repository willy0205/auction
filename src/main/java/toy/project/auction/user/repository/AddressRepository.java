package toy.project.auction.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.project.auction.user.domain.Address;
import toy.project.auction.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

  Optional<List<Address>> findAllByUserId(User user);
  Optional<Address> findByIdAndUserId(long id, User userId);
}
