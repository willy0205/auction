package toy.project.auction.feed.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
public class FeedRequest {
  private long feedId;
  private long userId;
  private String contents;
  private List<MultipartFile> imageList;
}
