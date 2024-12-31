package toy.project.auction.feed.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import toy.project.auction.common.model.ResponseDto;
import toy.project.auction.common.model.ResponseHandler;
import toy.project.auction.feed.model.FeedRequest;
import toy.project.auction.feed.model.FeedResponse;
import toy.project.auction.feed.service.FeedService;

import java.util.List;

@RestController
@RequestMapping("api/feed")
@RequiredArgsConstructor
public class FeedController {

  private final ResponseHandler responseHandler;

  private final FeedService feedService;

  @PostMapping("register")
  public ResponseEntity<ResponseDto<Void>> registerFeed(@ModelAttribute FeedRequest feedRequest) {
    // user id 추출
    long userid = 1;
    feedRequest.setUserId(userid);
    feedService.registerFeed(feedRequest);
    return responseHandler.handleRequest(() -> null);
  }

  // 굳이 필요할까? 라는 생각
  @GetMapping("detail")
  public ResponseEntity<ResponseDto<FeedResponse>> selectFeedDetail() {
    return null;
  }

  // user
  @GetMapping("list")
  public ResponseEntity<ResponseDto<List<FeedResponse>>> selectFeedList(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
    // user id 추출
    long userid = 1;
    List<FeedResponse> feedResponseList = feedService.selectFeedList(userid, page, size);
    return responseHandler.handleRequest(() -> feedResponseList);
  }

  // 나중에 팔로잉하는 사람들의 모든 피드를 가지고 오는 API
//  @GetMapping("all/list")
//  public ResponseEntity<ResponseDto<FeedResponse>> selectAllFeedList() {
//    return null;
//  }

}
