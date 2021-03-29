package com.mobiquity.service;

import com.mobiquity.domain.Pack;
import com.mobiquity.exception.APIException;
import com.mobiquity.repository.DataParserImplCustomized;
import com.mobiquity.repository.DataProviderImplFileReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


class DataProviderImplFileReaderTest {


    private static DataProviderImplFileReader dataProvider ;

    @BeforeAll
    static void beforeAll() throws APIException {
        String path = DataProviderImplFileReaderTest.class.getResource("/example_input").getPath();
        dataProvider = new DataProviderImplFileReader(path, new DataParserImplCustomized());
    }

    @RepeatedTest(4)
    void readOneRowFromDataSource() throws APIException {
        //given
        //when
        Optional<Pack> pack = dataProvider.readNextRowFromDataSource();
        //then
        assertTrue(pack.isPresent());
        assertThat(pack.get().getPossibleItems()).isNotNull();
    }

}