package toy.project.auction.feed.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import toy.project.auction.feed.domain.Feed;
import toy.project.auction.feed.domain.FeedImage;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // null인 필드는 직렬화하지 않음
public class FeedResponse {

  private long id;
  private String contents;
  private Integer likeCount;
  private long commentsCount;
  private boolean visibility;
  private Date registerTime;
  private Date updateTime;
  private List<String> imageList;

  public static FeedResponse fromFeed(Feed feed, List<String> imageList) {
    if (feed == null || imageList == null) {
      return null;
    }
    return FeedResponse.builder()
        .id(feed.getId())
        .contents(feed.getContents())
        .likeCount(feed.getLikeCount())
        .commentsCount(feed.getCommentsCount())
        .visibility(feed.getVisibility())
        .registerTime(feed.getRegisterTime())
        .updateTime(feed.getUpdateTime())
        .imageList(imageList)
        .build();
  }

}
