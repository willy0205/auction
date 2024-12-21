package toy.project.auction.user.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddressResponse {

  private long id;
  private String address;

}
