package toy.project.auction.common.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class ResponseHandler {

  public <T> ResponseEntity<ResponseDto<T>> handleRequest(Supplier<T> supplier) {
    try {
      T data = supplier.get();
      return createSuccessResponse(data);
    } catch (Exception e) {
      return createErrorResponse(e.getMessage());
    }
  }

  private <T> ResponseEntity<ResponseDto<T>> createSuccessResponse(T data) {
    ResponseDto<T> response = ResponseDto.<T>builder()
        .success(true)
        .message("Request processed successfully.")
        .data(data)
        .build();

    return ResponseEntity.ok(response);
  }

  private <T> ResponseEntity<ResponseDto<T>> createErrorResponse(String errorMessage) {
    ResponseDto<T> response = ResponseDto.<T>builder()
        .success(false)
        .message("Request failed.")
        .error(errorMessage)
        .build();

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

}
