package com.mobiquity.domain;

import com.mobiquity.exception.APIException;
import com.mobiquity.exception.ExceptionCodes;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void testBuilderHappy() throws APIException {
        //given
        //when
        Item itemTest =Item.builder()
                .index(1)
                .weight(new BigDecimal(10.1))
                .price(BigDecimal.valueOf(12.124))
                .build();
        //then
        System.out.println(itemTest.toString());
        assertThat(itemTest.getPrice()).isEqualTo("12.13");
        assertThat(itemTest.getWeight()).isEqualTo("10.10");
    }

    @Test
    void testBuilderSad()  {
        //given
        //when
        Throwable thrown = catchThrowable(() -> {
            Item.builder()
                    .index(1)
                    .weight(new BigDecimal(100.1))
                    .price(BigDecimal.valueOf(12.124))
                    .build();
        });
        //then
        Assertions.assertThat(thrown)
                .isInstanceOf(APIException.class)
                .hasMessageContaining(ExceptionCodes.FIELD_FORMAT_EXP.getExceptionMessage());

    }
}