package toy.project.auction.feed.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import toy.project.auction.auction.enums.ImageType;
import toy.project.auction.common.util.FileUploadUtil;
import toy.project.auction.feed.domain.Feed;
import toy.project.auction.feed.domain.FeedImage;
import toy.project.auction.user.domain.User;
import toy.project.auction.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FeedDataInitializer implements CommandLineRunner, Ordered {

  private final FeedImageRepository feedImageRepository;

  private final FeedRepository feedRepository;

  private final UserRepository userRepository;

  private final FileUploadUtil fileUploadUtil;

  @Override
  public void run(String... args) throws Exception {
    if (feedRepository.count() == 0) {

      User user = userRepository.findById(1L).orElseThrow();
      List<Feed> list = new ArrayList<>();
      List<FeedImage> feedImageList = new ArrayList<>();

      Feed feed = Feed.builder()
          .userId(user)
          .contents("날씨가 너무 좋다! ☀\uFE0F 오늘은 친구랑 공원에서 소풍 가기로 했어. #소풍 #햇살 #행복")
          .build();
      list.add(feed);

      Feed feed1 = Feed.builder()
          .userId(user)
          .contents("맛집 탐방! \uD83C\uDF7D\uFE0F 오늘은 새로운 이탈리안 레스토랑에서 파스타를 먹었는데, 진짜 맛있었어! #맛집 #이탈리안 #먹스타그램")
          .build();
      list.add(feed1);

      Feed feed2 = Feed.builder()
          .userId(user)
          .contents("오랜만에 독서 시간! \uD83D\uDCDA 좋은 책을 발견했는데, 정말 흥미진진해. 주말엔 이 책과 함께! #독서 #책스타그램 #주말계획")
          .build();
      list.add(feed2);

      FeedImage feedImage = FeedImage.builder()
          .imageType(ImageType.FEED)
          .imageUrl("https://picsum.photos/400/400")
          .fileSize(0)
          .originalFileName("윈터")
          .savingFileName("51296e5a-373e-4086-a3a4-433a2af7d2fe-윈터.jpg")
          .feed(feed)
          .build();
      feedImageList.add(feedImage);

      FeedImage feedImage2 = FeedImage.builder()
          .imageType(ImageType.FEED)
          .imageUrl("https://picsum.photos/400/500")
          .fileSize(0)
          .originalFileName("카리나")
          .savingFileName("1171d1a4-8e3f-47fa-aff7-281be20d3d24-카리나.jpg")
          .feed(feed)
          .build();
      feedImageList.add(feedImage2);

      FeedImage feedImage3 = FeedImage.builder()
          .imageType(ImageType.FEED)
          .imageUrl("https://picsum.photos/500/500")
          .fileSize(0)
          .originalFileName("윈터")
          .savingFileName("51296e5a-373e-4086-a3a4-433a2af7d2fe-윈터.jpg")
          .feed(feed1)
          .build();
      feedImageList.add(feedImage3);

      FeedImage feedImage4 = FeedImage.builder()
          .imageType(ImageType.FEED)
          .imageUrl("https://picsum.photos/500/400")
          .fileSize(0)
          .originalFileName("윈터")
          .savingFileName("51296e5a-373e-4086-a3a4-433a2af7d2fe-윈터.jpg")
          .feed(feed2)
          .build();
      feedImageList.add(feedImage4);

      feedRepository.saveAll(list);
      feedImageRepository.saveAll(feedImageList);


      System.out.println("Test user's feed data created.");
    }

  }

  @Override
  public int getOrder() {
    return 1;
  }
}
