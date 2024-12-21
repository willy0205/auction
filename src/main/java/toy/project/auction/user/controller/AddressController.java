package toy.project.auction.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.project.auction.common.model.ResponseDto;
import toy.project.auction.common.model.ResponseHandler;
import toy.project.auction.user.model.AddressRequest;
import toy.project.auction.user.model.AddressResponse;
import toy.project.auction.user.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("address")
@RequiredArgsConstructor
public class AddressController {

  private final ResponseHandler responseHandler;

  private final AddressService addressService;

  @PostMapping("register")
  public ResponseEntity<ResponseDto<Void>> registerUserAddress(@RequestBody AddressRequest addressRequest) {
    // user id 추출
    long userId = 1;
    addressRequest.setUserId(userId);
    addressService.registerUserAddress(addressRequest);
    return responseHandler.handleRequest(() -> null);
  }

  @GetMapping("list")
  public ResponseEntity<ResponseDto<List<AddressResponse>>> selectUserAddressList() {
    // user id 추출
    long userId = 1;
    return responseHandler.handleRequest(() -> addressService.selectUserAddressList(userId));
  }

  @GetMapping("detail/{addressId}")
  public ResponseEntity<ResponseDto<AddressResponse>> selectUserAddress(@PathVariable long addressId) {
    // user id 추출
    long userId = 1;
    return responseHandler.handleRequest(() -> addressService.selectUserAddress(userId, addressId));
  }

  @PutMapping("update")
  public ResponseEntity<ResponseDto<AddressResponse>> updateUserAddress(@RequestBody AddressRequest addressRequest) {
    // user id 추출
    long userId = 1;
    addressRequest.setUserId(userId);
    return responseHandler.handleRequest(() -> addressService.updateUserAddress(addressRequest));
  }

}
