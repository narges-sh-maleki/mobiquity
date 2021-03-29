package com.mobiquity.service;

import com.mobiquity.domain.Pack;
import com.mobiquity.exception.APIException;
import com.mobiquity.repository.DataParserImplCustomized;
import com.mobiquity.repository.DataProviderImplFileReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DataProviderImplFileReaderTest {



    String path = DataProviderImplFileReaderTest.class.getResource("/example_input").getPath();
    @Mock
    DataParserImplCustomized mockedParser;

    @Captor
    ArgumentCaptor<String> argumentCaptor;


    DataProviderImplFileReader dataProvider ;

    @RepeatedTest(4)
    void readOneRowFromDataSource() throws APIException {
        //given

        dataProvider = new DataProviderImplFileReader(path, new DataParserImplCustomized());
        //when
        Optional<Pack> pack = dataProvider.readNextRowFromDataSource();

        //then
        assertTrue(pack.isPresent());
        assertThat(pack.get().getPossibleItems()).isNotNull();
    }


    @Test
    void readNextRowMockedParser() throws APIException {
        //given
        Pack pack = Pack.builder().capacity(81).possibleItems (Collections.emptyList()).build();
        argumentCaptor = ArgumentCaptor.forClass(String.class);
        when(mockedParser.parseLine(argumentCaptor.capture() )).thenReturn(pack);
        dataProvider = new DataProviderImplFileReader(path,mockedParser);

        //when
        Optional<Pack> resultPack = dataProvider.readNextRowFromDataSource();
        //then
       assertThat(argumentCaptor.getValue()).isEqualTo("81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)");
    }

    @AfterEach
    void tearDown() throws IOException {
        dataProvider.closeFile();
    }
}