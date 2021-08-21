package org.example.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON 반환 컨트롤러로 만들어줌.
public class HelloController {

    @GetMapping("/hello")
    public String hello () { // HTTP 메소드인 GET의 요청을 받는 API로 만들어줌
        return "hello";
    }
}
