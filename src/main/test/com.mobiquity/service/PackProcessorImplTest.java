package com.mobiquity.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PackProcessorImplTest {

    private PackProcessorImpl packProcessor;

    @BeforeEach
    void setUp() {
        packProcessor = new PackProcessorImpl();
    }

    @ParameterizedTest()
    @MethodSource("provideSomeValidData")
    void fillPackage(Integer capacity,int[] weight, int[] cost,List expectedResult) {
        //given
        //when
        List<Integer> result = packProcessor.fillPackage(capacity,weight,cost);
        //then
        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideSomeValidData() {
        return Stream.of(

                Arguments.of(8100, new int[]{5338,8862,7848,7230,3018,4634},new int[]{45,98,3,76,9,48}, List.of(4))



        );
    }

}