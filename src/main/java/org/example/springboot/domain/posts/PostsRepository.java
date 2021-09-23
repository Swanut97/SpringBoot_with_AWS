package org.example.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") // 쿼리문
        // JavaDataJpa에서 제공하지 않는 메소드를 사용하지 않고 이처럼 사용 가능
        // SpringDataJpa에서 제공하는 기본 메소드만으로도 해결 가능 => 하지만 @Query가 가동성이 좋음
    List<Posts> findAllDesc();
}
