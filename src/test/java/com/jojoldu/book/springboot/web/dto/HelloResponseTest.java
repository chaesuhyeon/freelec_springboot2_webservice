package com.jojoldu.book.springboot.web.dto;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class HelloResponseTest {

    @Test
    public void 롬복_기능_테스트(){

        //given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        Assertions.assertThat(dto.getName()).isEqualTo(name);
        Assertions.assertThat(dto.getAmount()).isEqualTo(amount);

    }
}
