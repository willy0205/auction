package toy.project.auction.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.project.auction.user.domain.Address;
import toy.project.auction.user.domain.User;
import toy.project.auction.user.model.AddressRequest;
import toy.project.auction.user.model.AddressResponse;
import toy.project.auction.user.repository.AddressRepository;
import toy.project.auction.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

  private final AddressRepository addressRepository;
  private final UserRepository userRepository;

  public void registerUserAddress(AddressRequest addressRequest) {
    User user = userRepository.findById(addressRequest.getUserId()).orElseThrow();
    Address address = Address.builder()
        .address(addressRequest.getAddress())
        .user(user)
        .build();
    addressRepository.save(address);
  }

  public List<AddressResponse> selectUserAddressList(long userId) {
    User user = userRepository.findById(userId).orElseThrow();
    List<Address> addressList = addressRepository.findAllByUserId(user).orElseThrow();
    return addressList.stream().map(address -> AddressResponse.builder()
          .id(address.getId())
          .address(address.getAddress())
          .build()
    ).collect(Collectors.toList());
  }

  public AddressResponse selectUserAddress(long userId, long addressId) {
    User user = userRepository.findById(userId).orElseThrow();
    Address address = addressRepository.findByIdAndUserId(addressId, user).orElseThrow();
    return AddressResponse.builder()
        .address(address.getAddress())
        .id(address.getId())
        .build();
  }

  public AddressResponse updateUserAddress(AddressRequest addressRequest) {
    User user = userRepository.findById(addressRequest.getUserId()).orElseThrow();
    Address address = addressRepository.findByIdAndUserId(addressRequest.getAddressId(), user).orElseThrow();
    address.setAddress(addressRequest.getAddress());
    addressRepository.save(address);
    return AddressResponse.builder()
        .address(address.getAddress())
        .id(address.getId())
        .build();
  }
}
