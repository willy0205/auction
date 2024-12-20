package toy.project.auction.auction.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import toy.project.auction.auction.model.AuctionListResponse;
import toy.project.auction.auction.model.AuctionRequest;
import toy.project.auction.auction.service.AuctionService;
import toy.project.auction.common.model.ResponseDto;

@RestController
@RequestMapping("auction")
@RequiredArgsConstructor
public class AuctionController {

private final AuctionService auctionService;

  @PostMapping("register")
  public ResponseEntity<ResponseDto<Void>> registerAuction(@Valid @ModelAttribute AuctionRequest auctionRequest) {

    try {
      // user id 추출
      long userId = 1;
      auctionRequest.setSetterId(userId);
      auctionService.registerAuction(auctionRequest);

      // 성공적인 응답 반환
      ResponseDto<Void> response = ResponseDto.<Void>builder()
          .success(true)
          .message("Auction registered successfully.")
          .data(null) // 데이터가 필요 없으면 null
          .build();

      return ResponseEntity.ok(response);
    } catch (Exception e) {
      // 예외 발생 시 에러 응답 반환
      ResponseDto<Void> response = ResponseDto.<Void>builder()
          .success(false)
          .message("Failed to register auction.")
          .error(e.getMessage()) // 에러 메시지
          .build();

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @GetMapping("list")
  public ResponseEntity<ResponseDto<AuctionListResponse>> selectUserAuctionList(@RequestParam(defaultValue = "0") int page,
                                                                                @RequestParam(defaultValue = "10") int size) {
    // user id 추출
    long userId = 1;
    AuctionListResponse auctionListResponse = auctionService.selectUserAuctionList(page, size, userId);

    try {

      // 성공적인 응답 반환
      ResponseDto<AuctionListResponse> response = ResponseDto.<AuctionListResponse>builder()
          .success(true)
          .message("Auction registered successfully.")
          .data(auctionListResponse) // 데이터가 필요 없으면 null
          .build();

      return ResponseEntity.ok(response);
    } catch (Exception e) {
      // 예외 발생 시 에러 응답 반환
      ResponseDto<AuctionListResponse> response = ResponseDto.<AuctionListResponse>builder()
          .success(false)
          .message("Failed to register auction.")
          .error(e.getMessage()) // 에러 메시지
          .build();

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

  }

}
