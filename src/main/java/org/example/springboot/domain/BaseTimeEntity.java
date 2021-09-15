package org.example.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity 클래스들이 이 클래스를 상속시 이 클래스의 맴버도 칼럼으로 인식하게 한다
@EntityListeners(AuditingEntityListener.class) // 이 클래스에 Auditing 기능을 포함시킨다
public abstract class BaseTimeEntity {

    @CreatedDate // Entity 생성하여 저장한 시간을 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate // 조회한 Entity를 변경한 시간을 자동 저장
    private LocalDateTime modifiedDate;
}
