package toy.project.auction.feed.model;

import lombok.Getter;

import java.util.Date;

@Getter
public class FeedResponse {

  private long id;
  private String contents;
  private Integer likeCount;
  private long commentsCount;
  private boolean visibility;
  private Date registerTime;
  private Date updateTime;

}
