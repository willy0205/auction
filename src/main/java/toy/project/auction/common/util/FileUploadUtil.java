package toy.project.auction.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import toy.project.auction.auction.enums.ImageType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class FileUploadUtil {

  @Value("${file.upload-dir-content}")
  private String uploadContentDir;

  @Value("${file.upload-dir-thumbnail}")
  private String uploadThumbnailDir;

  // 업로드 디렉토리 생성
  private void createUploadDirectory(ImageType imageType) {
    String directoryPath = imageType.equals(ImageType.THUMB) ? uploadThumbnailDir : uploadContentDir;
    File directory = new File(directoryPath);
    if (!directory.exists()) {
      directory.mkdirs();
    }
  }

  // 파일 저장 메소드
  private String saveFile(MultipartFile file, String directory) throws IOException {
    String savingFilename = UUID.randomUUID() + "-" + file.getOriginalFilename();
    String filePath = directory + File.separator + savingFilename;
    file.transferTo(new File(filePath)); // 파일 저장
    return filePath;
  }

  // 썸네일 이미지 업로드
  public Optional<String> uploadThumbnailImage(MultipartFile file) {
    createUploadDirectory(ImageType.THUMB);

    if (file.isEmpty()) {
      return Optional.empty(); // 파일이 비어있는 경우
    }

    try {
      String filePath = saveFile(file, uploadThumbnailDir);
      return Optional.of(filePath); // 성공 시 파일 경로 반환
    } catch (IOException e) {
      System.err.println("파일 업로드 실패: " + e.getMessage());
      return Optional.empty(); // 실패 시 빈 Optional 반환
    }
  }

  public Optional<String> uploadContentImage(MultipartFile file) {
    createUploadDirectory(ImageType.CONTENT);

    if (file.isEmpty()) {
      return Optional.empty(); // 파일이 비어있는 경우
    }

    try {
      String filePath = saveFile(file, uploadContentDir);
      return Optional.of(filePath); // 성공 시 파일 경로 반환
    } catch (IOException e) {
      System.err.println("파일 업로드 실패: " + e.getMessage());
      return Optional.empty(); // 실패 시 빈 Optional 반환
    }
  }


  // 콘텐츠 이미지 업로드
  public Optional<List<String>> uploadContentImages(List<MultipartFile> files) {
    List<String> result = new ArrayList<>();

    createUploadDirectory(ImageType.CONTENT);

    if (files.isEmpty()) {
      return Optional.empty(); // 파일이 비어있는 경우
    }

    try {
      for (MultipartFile file : files) {
        String filePath = saveFile(file, uploadContentDir);
        result.add(filePath);
      }
      return Optional.of(result); // 성공 시 파일 경로 반환
    } catch (IOException e) {
      System.err.println("파일 업로드 실패: " + e.getMessage());
      return Optional.empty(); // 실패 시 빈 Optional 반환
    }
  }
}
