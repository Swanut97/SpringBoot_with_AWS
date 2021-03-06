package org.example.springboot.web;

import org.example.springboot.config.auth.LoginUser;
import org.example.springboot.config.auth.dto.SessionUser;
import org.example.springboot.service.posts.PostsService;
import org.example.springboot.web.dto.PostsResponseDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) { // model: 서버 템플릿 엔진에서 사용할 수 있는 객체로 저장
            // 여기서 가져온 결과를 posts로 index.mustache에 전달
        model.addAttribute("posts", postsService.findAllDesc());
        
        // 반복적 사용을 지양하기 위해 제거 // 대신 파라미터에 해당 라인을 대체할 @LoginUser SessionUser user를 추가
        // SessionUser user = (SessionUser) httpSession.getAttribute("user"); // 로그인 성공시 세션에 저장된 SessionUser를 가져옴
        
        if (user != null) { // 세션에 저장된 값이 있을 때만 model에서 userName으로 등록, 없으면 model엔 아묷도 없고 로그인 버튼이 보이게 됨.
            model.addAttribute("user", user); // index.mustach와 연동하기위해 userName을 주는 것이 아닌 user를 주고
        }                                                   // 그로부터 이름을 직접 꺼내도록 유도

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);

        model.addAttribute("post", dto);

        return "posts-update";
    }
}
