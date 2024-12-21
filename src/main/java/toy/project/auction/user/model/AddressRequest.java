package toy.project.auction.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {
  private long addressId;
  private String address;
  private long userId;
}
