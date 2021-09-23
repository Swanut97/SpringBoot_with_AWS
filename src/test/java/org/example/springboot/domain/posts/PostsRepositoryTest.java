package org.example.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After // Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정
    public void cleanUp() { // 여러 테스트가 동시 수행되면 테스트용 DB인 H2에 데이터가 남아
        postsRepository.deleteAll(); // 다음테스트에 실패할 가능성 존재 => deleteAll
    }

    @Test
    public void 게시글저장_불러오기() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        
        // 앞서 언급되었던 빌더 패턴이 사용된 케이스
        postsRepository.save(Posts.builder() // 테이블 posts에 insert, update 쿼리를 수행
                .title(title)                // 이미 존재하는 id라면 update
                .content(content)            // 존재하지 않는 id라면 insert
                .author("zerfrawin@gmail.com") // 여기서 id는 @Id => PK를 의미
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll(); // 테이블 posts에 있는 모든 데이터를 조회하는 메소드

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        // given
        LocalDateTime now = LocalDateTime.of(2021, 9, 15, 20, 1, 33); // 버릇처럼 초를 뺐는데, 초까지 기입 가능
        postsRepository.save(Posts.builder()
                        .title("title")
                        .content("content")
                        .author("author")
                        .build());
        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>>> createDate=" +posts.getCreatedDate() +", modifiedDate =" +posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
