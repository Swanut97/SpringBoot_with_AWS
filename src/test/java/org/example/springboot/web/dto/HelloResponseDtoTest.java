package org.example.springboot.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬봄_기눙_테스트() {
        // given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name);      // assertj: 테스트 검증 라이브러리
        assertThat(dto.getAmount()).isEqualTo(amount);  // assertThat: 검증 대상을 인자로
                                                        //             isEqualTo 같은 메소드를 이어 사용 가능
                                                        // isEqualTo: assertj의 동등 비교 메소드
    }
}
