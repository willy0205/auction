package toy.project.auction.auction.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import toy.project.auction.auction.domain.Auction;
import toy.project.auction.auction.domain.AuctionImage;
import toy.project.auction.auction.enums.AuctionStatus;
import toy.project.auction.auction.enums.CurrencyUnit;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuctionResponse {
  private Long id; // 경매 ID
  private String title; // 경매 제목
  private String summary; // 경매 요약
  private String description; // 경매 설명
  private Long startingPrice; // 시작 가격
  private Long currentPrice; // 현재 가격
  private CurrencyUnit currencyUnit; // 통화 단위
  private Date registerTime; // 등록 시간
  private Date updateTime; // 수정 시간
  private Date startTime; // 시작 시간
  private Date endTime; // 종료 시간
  private AuctionStatus status; // 경매 상태
  private Integer bidIncrement; // 입찰 증가 금액
  private String thumbnailPath;
  private List<String> contentPathList;

  // Auction 엔티티를 AuctionResponseDto로 변환하는 메소드
  public static AuctionResponse fromAuction(Auction auction, AuctionImage thumbnailPath, List<AuctionImage> contentPathList) {
    if (auction == null) {
      return null;
    }
    return AuctionResponse.builder()
        .id(auction.getId())
        .title(auction.getTitle())
        .summary(auction.getSummary())
        .description(auction.getDescription())
        .startingPrice(auction.getStartingPrice())
        .currentPrice(auction.getCurrentPrice())
        .currencyUnit(auction.getCurrencyUnit())
        .registerTime(auction.getRegisterTime())
        .updateTime(auction.getUpdateTime())
        .startTime(auction.getStartTime())
        .endTime(auction.getEndTime())
        .status(auction.getStatus())
        .bidIncrement(auction.getBidIncrement())
        .thumbnailPath(thumbnailPath.getImageUrl())
        .contentPathList(contentPathList.stream().map(AuctionImage::getImageUrl).collect(Collectors.toList()))
        .build();
  }
}
