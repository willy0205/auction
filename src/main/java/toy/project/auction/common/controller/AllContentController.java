package toy.project.auction.common.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import toy.project.auction.common.model.ResponseDto;

import java.util.List;

@RestController
@RequestMapping("api/all")
public class AllContentController {

  @GetMapping("list")
  public ResponseEntity<ResponseDto<List<?>>> selectUserAllContent(@RequestParam(defaultValue = "0") int page,
                                                                              @RequestParam(defaultValue = "10") int size) {


    return null;
  }

}
