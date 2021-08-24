package org.example.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // 스프링부트테스트와 JUnit사이의 연결자
@WebMvcTest // web에 집중하는 어노테이션
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 bean을 주입
    private MockMvc mvc; // 스프링 MVC 테스트의 시작점. 이 클래스로 HTTP API 테스트

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // /hello 주소로 get요청
                .andExpect(status().isOk()) // perform 결과를 검증. HTTP Header의 status검증
                .andExpect(content().string(hello)); // perform 결과를 검증. 응답 본문 내용 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {  // 책에선 이것만 했는데 되지만
        String name = "hello";                          // 난 왜인지 안됨. -> HelloController에 GetMapping 추가하니 됨,
        int amount = 1000;                              // 이유를 알아 볼 예정.

        mvc.perform(
                    get("/hello/dto")
                            .param("name", name)    //
                            .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
/*************************************************************
 * 테스트 코드를 작성하는 이유
 * for 단위테스트
 *  개발 초기에 문제를 발견할 수 있게 해준다.
 *  => 빠른 피드백
 *
 * 테스트 코드 작성 -> 실행 -> 검증
 *************************************************************/