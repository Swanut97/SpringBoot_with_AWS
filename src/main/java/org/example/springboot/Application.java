package org.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA auditing 활성화
@SpringBootApplication // 여기부터 읽어가므로  프로젝트 최상단에 존재해야함.
public class Application {
    public static void main (String[] args) {
        SpringApplication.run(Application.class, args); // 내장 WAS 실행 (Web Application Server)
    }
}