package org.example.springboot.web;

import org.example.springboot.config.auth.SecurityConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // 스프링부트테스트와 JUnit사이의 연결자
@WebMvcTest(controllers = HelloController.class,
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
    }) // web에 집중하는 어노테이션
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 bean을 주입
    private MockMvc mvc; // 스프링 MVC 테스트의 시작점. 이 클래스로 HTTP API 테스트

    @WithMockUser(roles="USER")
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // /hello 주소로 get요청
                .andExpect(status().isOk()) // perform 결과를 검증. HTTP Header의 status검증
                .andExpect(content().string(hello)); // perform 결과를 검증. 응답 본문 내용 검증
    }

    @WithMockUser(roles="USER")
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                    get("/hello/dto")
                            .param("name", name)    // param: API 테스트시 요청 파라미터 설정 (String만 가능)
                            .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))    // jsonPath: JSON 응답값을 필드별로 검증할 수 있는 메소드
                .andExpect(jsonPath("$.amount", is(amount))); // $을 기준으로 필드명을 명시
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