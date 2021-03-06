package org.example.springboot.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING) // JPA 데이터베이스에 저장할 때의 Enum 값을 결정 // 기본적으론 int
    @Column(nullable = false)    //  숫자로 저장되면 데이터베이스에서 확인시 무슨 코드인지 확인 불가 => String으로 저장
    private Role role;

    @Builder
    public User (String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update (String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey () {
        return this.role.getKey();
    }
}
