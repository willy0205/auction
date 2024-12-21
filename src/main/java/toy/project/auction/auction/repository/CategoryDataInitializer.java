package toy.project.auction.auction.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import toy.project.auction.auction.domain.Categroy;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class CategoryDataInitializer implements CommandLineRunner {

  private final CategoryRepository categoryRepository;

  @Override
  public void run(String... args) throws Exception {
    if (categoryRepository.count() == 0) {
      Categroy categroy1 = Categroy.builder()
          .name("의류")
          .description("옷이다.")
          .build();

      Categroy categroy2 = Categroy.builder()
          .name("전자제품")
          .description("전기로 직인다.")
          .build();
      categoryRepository.saveAll(Arrays.asList(categroy1, categroy2));

      System.out.println("Test categories created.");
    }
  }
}
