package toy.project.auction.auction.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import toy.project.auction.auction.enums.AuctionStatus;
import toy.project.auction.auction.enums.CurrencyUnit;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class AuctionRequest {
  private long setterId;
  private long categoryId;
  private String title;
  private String summary;
  private String description;
  private long startingPrice;
  private long currentPrice;
  private CurrencyUnit currencyUnit;
  private Date registerTime;
  private Date updateTime;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date startTime;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date endTime;
  private AuctionStatus status;
  private Integer bidIncrement;
  @NotNull
  private MultipartFile thumbnailImage;
  private List<MultipartFile> imageList;
}
