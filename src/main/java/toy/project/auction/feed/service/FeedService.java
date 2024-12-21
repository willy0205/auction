package toy.project.auction.feed.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import toy.project.auction.auction.enums.ImageType;
import toy.project.auction.common.util.FileUploadUtil;
import toy.project.auction.feed.domain.Feed;
import toy.project.auction.feed.domain.FeedImage;
import toy.project.auction.feed.model.FeedRequest;
import toy.project.auction.feed.model.FeedResponse;
import toy.project.auction.feed.repository.FeedImageRepository;
import toy.project.auction.feed.repository.FeedRepository;
import toy.project.auction.user.domain.User;
import toy.project.auction.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedService {

  private final FeedRepository feedRepository;

  private final FeedImageRepository feedImageRepository;

  private final FileUploadUtil fileUploadUtil;

  private final UserRepository userRepository;

  public void registerFeed(FeedRequest feedRequest) {
    User user = userRepository.findById(feedRequest.getUserId()).orElseThrow();
    Feed feed = Feed.builder()
        .userId(user)
        .contents(feedRequest.getContents())
        .build();

    // 이미지 저장 우선
    List<FeedImage> feedImageList = feedRequest.getImageList().stream().map(multipartFile -> {
      String filePath = fileUploadUtil.uploadFeedFile(multipartFile).orElseThrow();
      return FeedImage.builder()
          .imageType(ImageType.FEED)
          .fileSize(multipartFile.getSize())
          .savingFileName(filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length()))
          .originalFileName(multipartFile.getOriginalFilename())
          .feed(feed)
          .imageUrl(filePath)
          .build();
    }).toList();

    feedRepository.save(feed);
    feedImageRepository.saveAll(feedImageList);

  }

  public List<FeedResponse> selectFeedList(long userId, int page, int size) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

    Pageable pageable = PageRequest.of(page, size);
    List<Feed> feedList = feedRepository.findAllByUserId(user, pageable)
        .orElseThrow(() -> new EntityNotFoundException("No feeds found for user ID: " + userId));

    return feedList.stream()
        .map(this::convertToFeedResponse) // 변환 메서드 호출
        .toList(); // List<FeedResponse>로 수집
  }

  // 별도의 변환 메서드 정의
  private FeedResponse convertToFeedResponse(Feed feed) {
    List<FeedImage> feedImageList = feedImageRepository.findAllByFeed(feed)
        .orElseThrow(() -> new EntityNotFoundException("No images found for feed ID: " + feed.getId()));

    List<String> imageUrlList = feedImageList.stream()
        .map(FeedImage::getImageUrl)
        .collect(Collectors.toList());

    return FeedResponse.fromFeed(feed, imageUrlList);
  }


//  public List<FeedResponse> selectFeedList(FeedRequest feedRequest, int page, int size) {
//    User user = userRepository.findById(feedRequest.getUserId()).orElseThrow();
//    Pageable pageable = PageRequest.of(page, size);
//    List<Feed> feedList = feedRepository.findAllByUserId(user, pageable).orElseThrow();
//    feedList.stream().map(feed -> {
//      List<FeedImage> feedImageList = feedImageRepository.findAllByFeed(feed).orElseThrow();
//      List<String> imageUrlList = feedImageList.stream()
//          .map(FeedImage::getImageUrl)
//          .toList();
//      return FeedResponse.fromFeed(feed, imageUrlList);
//    }).toList();
//
//
//    return null;
//  }

}
