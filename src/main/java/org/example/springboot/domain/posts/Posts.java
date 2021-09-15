package org.example.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springboot.domain.BaseTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter // 클래스 내 모든 필드의 Getter 메소드 자동 생성
@NoArgsConstructor // 디폴트 생성자 자동 추가
@Entity // 테이블과 링크될 클래스임을 명시
public class Posts extends BaseTimeEntity {

    @Id // 테이블의 PK(Primary Key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙 명시
    private Long id;

    @Column(length = 500, nullable = false) // 테이블의 컬럼을 나타냄
    private String title;                   // 선언하지 않아도 해당 클래스의 필드는 모두 컬럼이 됨
                                            // 근데 왜 선언? => 기본값 외에 변경하고 싶은 옵션이 있다면 사용
                                            // VARCHAR(255)를 500으로 쓰고 싶다던가,
                                            // TEXT로 사용하고 싶다던가 등...
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스 생성 // 빌더 패턴이란? 하단에 설명
             // 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

/********************************************************
 * 빌더 패턴 (Builder Pattern)
 *
 * 객체 생성 단계들을 캡슐화 하여 객체 생성을 유연하게 해주는 패턴
 * => 객체 생성과 객체 표현 방법을 분리
 * 객체가 거대해지거나 생성과정에서 인자의 순서가 바뀌어서 생기는 문제를 해소
 *
 * 예시)
 * Car myCar = new Car.Builder("현대")
 *                      .engine("독일엔진")
 *                      .name("제네시스")
 *                      .tire("한국타이어")
 *                      .capacity(4)
 *                      .price(70000000)
 *                      .build();   // 출처: https://bbaktaeho-95.tistory.com/72 [Bbaktaeho]
 *
 ********************************************************/
