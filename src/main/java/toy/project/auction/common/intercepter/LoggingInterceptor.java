package toy.project.auction.common.intercepter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.text.SimpleDateFormat;
import java.util.UUID;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);
  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss.SSS");

  // 요청 전 처리 (요청 시작 시간 기록)
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    // 요청 시작 시간 기록
    long startTime = System.currentTimeMillis();
    request.setAttribute("startTime", startTime);
    // 클라이언트 IP 주소
    String clientIp = request.getRemoteAddr();
    String requestId = UUID.randomUUID().toString();
    request.setAttribute("requestId", requestId);

    // 경로와 요청 메서드 정보
    String method = request.getMethod();
    String requestURI = request.getRequestURI();

    // 로그 출력
    long endTime = System.currentTimeMillis();
    long duration = endTime - startTime;

    logger.info("[{}] [{}] {} - Request received from IP: {}, processing time: {}ms, request time : {}, request id : {}",
        method, requestURI, "Request received", clientIp, duration, simpleDateFormat.format(startTime), requestId);


    return true; // 다음 단계로 요청 전달
  }

  // 응답 후 처리 (응답 시간 계산 및 로깅)
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    long startTime = (long) request.getAttribute("startTime");
    long endTime = System.currentTimeMillis();
    long duration = endTime - startTime;

    // 로그 출력: 요청 처리 완료, HTTP 메서드, 경로, 상태 코드, 요청 처리 시간, 응답 시간
    logger.info("[{}] [{}] {} - Status: {}, Duration: {} ms, Response time: {}, request id : {}",
        request.getMethod(),          // HTTP 메서드
        request.getRequestURI(),      // 요청 경로
        "Request completed",          // 고정 메시지
        response.getStatus(),         // 응답 상태 코드
        duration,                     // 요청 처리 시간
        simpleDateFormat.format(endTime),
        request.getAttribute("requestId")); // 응답 시간 (포맷된 시간)
  }
}