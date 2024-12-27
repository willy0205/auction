package toy.project.auction.auction.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import toy.project.auction.auction.model.AuctionListResponse;
import toy.project.auction.auction.model.AuctionRequest;
import toy.project.auction.auction.model.AuctionResponse;
import toy.project.auction.auction.service.AuctionService;
import toy.project.auction.common.model.ResponseDto;
import toy.project.auction.common.model.ResponseHandler;

@RestController
@RequestMapping("api/auction")
@RequiredArgsConstructor
public class AuctionController {

  private final ResponseHandler responseHandler;

  private final AuctionService auctionService;

  @PostMapping("register")
  public ResponseEntity<ResponseDto<Void>> registerAuction(@Valid @ModelAttribute AuctionRequest auctionRequest) {

    // user id 추출
    long userId = 1;
    auctionRequest.setSetterId(userId);
    auctionService.registerAuction(auctionRequest);

    return responseHandler.handleRequest(() -> null);
  }

  @GetMapping("list")
  public ResponseEntity<ResponseDto<AuctionListResponse>> selectUserAuctionList(@RequestParam(defaultValue = "0") int page,
                                                                                @RequestParam(defaultValue = "10") int size) {
    // user id 추출
    long userId = 1;
    AuctionListResponse auctionListResponse = auctionService.selectUserAuctionList(page, size, userId);
    return responseHandler.handleRequest(() -> auctionListResponse);
  }

  @GetMapping("detail/{auctionId}")
  public ResponseEntity<ResponseDto<AuctionResponse>> selectAuctionDetail(@PathVariable long auctionId) {
    // user id 추출
    long userId = 1;
    AuctionResponse auctionResponse = auctionService.selectAuctionDetail(auctionId, userId);
    return responseHandler.handleRequest(() -> auctionResponse);
  }

}
