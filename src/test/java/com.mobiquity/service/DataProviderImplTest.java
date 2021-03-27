package com.mobiquity.service;

import com.mobiquity.domain.Pack;
import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class DataProviderImplTest {


    private static DataProviderImpl dataProvider ;

    @BeforeAll
    static void beforeAll() throws APIException {
        String path = DataProviderImplTest.class.getResource("/example_input").getPath();
        dataProvider = new DataProviderImpl(path, new DataParser());
    }

    @RepeatedTest(4)
    void readOneRowFromDataSource() throws APIException {
        //given
        //when
        Pack pack = dataProvider.readOneRowFromDataSource();
        //then
        System.out.println(pack.toString());
        assertThat(pack).isNotNull();
        assertThat(pack.getPossibleItems()).isNotNull();

    }

}