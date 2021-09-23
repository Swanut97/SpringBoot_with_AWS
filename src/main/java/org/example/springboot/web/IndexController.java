package org.example.springboot.web;

import org.example.springboot.domain.posts.PostsRepository;
import org.example.springboot.web.dto.PostsResponseDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsRepository postsService;

    @GetMapping("/")
    public String index(Model model) { // model: 서버 템플릿 엔진에서 사용할 수 있는 객체로 저장
            // 여기서 가져온 결과를 posts로 index.mustache에 전달
        model.addAttribute("posts", postsService.findAllDesc());

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        // 뭔가 문제가 있어서 dto로 할당해서 파라미터로 주기가 불가능. 캐스팅 문제로 추정

        model.addAttribute("post", postsService.findById(id));

        return "posts-update";
    }
}
