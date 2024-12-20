package toy.project.auction.auction.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AuctionListResponse {

  private List<AuctionResponse> auctionResponse;
  private long totalCount;

}
