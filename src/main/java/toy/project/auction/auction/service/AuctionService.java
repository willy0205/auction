package toy.project.auction.auction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import toy.project.auction.auction.domain.Auction;
import toy.project.auction.auction.domain.AuctionImage;
import toy.project.auction.auction.enums.AuctionStatus;
import toy.project.auction.auction.enums.ImageType;
import toy.project.auction.auction.model.AuctionRequest;
import toy.project.auction.auction.model.AuctionResponse;
import toy.project.auction.auction.repository.AuctionImageRepository;
import toy.project.auction.auction.repository.AuctionRepository;
import toy.project.auction.common.util.FileUploadUtil;
import toy.project.auction.user.domain.User;
import toy.project.auction.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionService {

  private final UserRepository userRepository;

  private final FileUploadUtil fileUploadUtil;

  private final AuctionRepository auctionRepository;

  private final AuctionImageRepository auctionImageRepository;

  public void registerAuction(AuctionRequest auctionRequest){

    User setter = userRepository.findById(auctionRequest.getSetterId()).orElseThrow();

    auctionRequest.setStatus(AuctionStatus.READY);

    // Auction DTO -> entity
    Auction auctionEntity = Auction.fromRequest(auctionRequest, setter);

    // 썸네일 이미지 파일 업로드
    String thumnailPath = fileUploadUtil.uploadThumbnailImage(auctionRequest.getThumbnailImage())
        .orElseThrow();

    List<AuctionImage> auctionImageList = new ArrayList<>();
    // 썸네일 이미지
    AuctionImage thumbnailImage = AuctionImage.builder()
        .imageType(ImageType.THUMB)
        .imageUrl(thumnailPath)
        .fileSize(auctionRequest.getThumbnailImage().getSize())
        .originalFileName(auctionRequest.getThumbnailImage().getOriginalFilename())
        .savingFileName(thumnailPath.substring(thumnailPath.lastIndexOf("/") + 1, thumnailPath.length()))
        .setter(setter)
        .auction(auctionEntity)
        .build();

    auctionImageList.add(thumbnailImage);

    // 콘텐츠 내용에 들어가는 이미지 파일 업로드
    for (MultipartFile file : auctionRequest.getImageList()) {
      String contentPath = fileUploadUtil.uploadContentImage(file)
          .orElseThrow();

      AuctionImage contentImage = AuctionImage.builder()
          .imageType(ImageType.CONTENT)
          .imageUrl(contentPath)
          .fileSize(file.getSize())
          .originalFileName(file.getOriginalFilename())
          .savingFileName(contentPath.substring(contentPath.lastIndexOf("/") + 1, contentPath.length()))
          .setter(setter)
          .auction(auctionEntity)
          .build();

      auctionImageList.add(contentImage);
    }

    // 업로드한 파일 entity로 저장하기
    auctionRepository.save(auctionEntity);
    auctionImageRepository.saveAll(auctionImageList);

  }

  public List<AuctionResponse> selectUserAuctionList(int page, int size, long userID) {

    auctionRepository.findAllBySetter(PageRequest.of(page, size)).getTotalPages();
    List<Auction> auctionList = auctionRepository.findAll(PageRequest.of(page, size)).getContent();

    List<AuctionResponse> result = new ArrayList<>();

    for (Auction auction: auctionList) {
      AuctionImage thumbnailPath = auctionImageRepository.findByAuctionAndImageType(auction, ImageType.THUMB).orElseThrow();
      List<AuctionImage> contentPath = auctionImageRepository.findAllByAuctionAndImageType(auction, ImageType.CONTENT).orElseThrow();
      AuctionResponse auctionResponse = AuctionResponse.fromAuction(auction, thumbnailPath, contentPath);
      result.add(auctionResponse);
    }

    return result;
  }

}
