package org.example.springboot.web.dto;

import lombok.Getter; // 선언된 모든 필드의 get메소드 생성
import lombok.RequiredArgsConstructor;  // 선언된 모든 final필드가 포함된 생성자를 생성
                                        // final이 없으면 생성자에 포함X

@Getter
@RequiredArgsConstructor
public class HelloResponseDto {

    private final String name;
    private final int amount;
}
