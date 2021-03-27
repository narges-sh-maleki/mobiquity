package com.mobiquity.service;

import com.mobiquity.domain.Pack;
import com.mobiquity.exception.APIException;
import com.mobiquity.exception.ExceptionCodes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class DataParserTest {

    DataParser dataParser = new DataParser();

    @ParameterizedTest
    @MethodSource("provideSomeValidData")
    void parseLineHappy(String inputData, Integer capacity, List<BigDecimal> weight, List<BigDecimal> price) throws APIException {
        //given
        //when
        Pack resultPack = dataParser.parseLine(inputData);
        //then
        assertThat(resultPack).isNotNull();
        assertThat(resultPack.getItemsWeight()).containsExactlyElementsOf((weight));
        assertThat(resultPack.getItemsPrice()).containsExactlyElementsOf(price);
    }

    @ParameterizedTest
    @MethodSource("provideSomeInvalidData")
    void parseLineSad(String inputData) {
        //given


        //when
        Throwable thrown = catchThrowable(() -> {
            dataParser.parseLine(inputData);
        });

        //then
        assertThat(thrown)
                .isInstanceOf(APIException.class)
                .hasMessageContaining(ExceptionCodes.FIELD_FORMAT_EXP.getExceptionMessage());

    }

    private static Stream<Arguments> provideSomeValidData() {
        return Stream.of(

                Arguments.of("81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) ",
                        81,
                        List.of(BigDecimal.valueOf(53.38), BigDecimal.valueOf(88.62), BigDecimal.valueOf(78.48)),
                        List.of(BigDecimal.valueOf(45), BigDecimal.valueOf(98), BigDecimal.valueOf(3)))
        );
    }

    private static Stream<Arguments> provideSomeInvalidData() {
        return Stream.of(
                Arguments.of("81 : (1,153.38,€45) (2,588.62,€98) (3,678.48,€3) "),
                Arguments.of("181 : (1,15.38,€45) ")
        );
    }
}