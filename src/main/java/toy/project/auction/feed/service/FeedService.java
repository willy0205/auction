package toy.project.auction.feed.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.project.auction.auction.enums.ImageType;
import toy.project.auction.common.util.FileUploadUtil;
import toy.project.auction.feed.domain.Feed;
import toy.project.auction.feed.domain.FeedImage;
import toy.project.auction.feed.model.FeedRequest;
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

}
