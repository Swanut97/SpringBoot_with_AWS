package org.example.springboot.web;

import org.example.springboot.web.dto.HelloResponseDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON 반환 컨트롤러로 만들어줌.
public class HelloController {

    @GetMapping("/hello")
    public String hello () { // HTTP 메소드인 GET의 요청을 받는 API로 만들어줌
        return "hello";
    }

    @GetMapping("/hello/dto") // 책에서는 언급해주지 않음. => 헬로컨트롤러테스트의 헬로디토가_리턴됨이 정상작동 X
                                // => 책을 자세히 보지 않은 본은의 미스: 책에 있었습니다...
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name,amount);
    }
}
