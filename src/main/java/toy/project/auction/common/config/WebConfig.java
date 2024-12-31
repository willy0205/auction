package toy.project.auction.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import toy.project.auction.common.intercepter.LoggingInterceptor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  private final LoggingInterceptor loggingInterceptor;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**") // 모든 경로에 대해
        .allowedOrigins("http://localhost:3000") // 허용할 출처
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
        .allowCredentials(true) // 쿠키를 허용할지 여부
        .maxAge(3600); // preflight 요청의 캐시 시간
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(loggingInterceptor)
        .addPathPatterns("/**"); // 모든 요청에 대해 Interceptor 적용
  }
}

