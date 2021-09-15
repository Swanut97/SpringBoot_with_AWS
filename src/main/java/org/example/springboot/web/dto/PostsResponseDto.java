package org.example.springboot.web.dto;

import lombok.Getter;
import org.example.springboot.domain.posts.Posts;

@Getter // 이거 않넣으면 /api/v1/posts/{id} 로 조회시
        // No converter found for return value of type: class org.example.springboot.web.dto.PostsResponseDto 에러를 볼 수 있음.
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto (Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
