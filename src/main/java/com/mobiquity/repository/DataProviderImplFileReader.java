package com.mobiquity.repository;

import com.mobiquity.domain.Pack;
import com.mobiquity.exception.APIException;
import com.mobiquity.exception.ExceptionCodes;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;


@Slf4j
public class DataProviderImplFileReader implements DataProvider {


    private final BufferedReader reader;
    private final DataParserImplCustomized dataParserImplCustomized;


    public DataProviderImplFileReader(String filePath, DataParserImplCustomized dataParserImplCustomized) throws APIException {

        this.dataParserImplCustomized = dataParserImplCustomized;
        try {
            this.reader = new BufferedReader
                    (new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            log.error("exception in reading from file", e);
            throw new APIException(ExceptionCodes.READ_FILE_EXP, e);
        }

    }

    public void closeFile() throws IOException {
        this.reader.close();
    }

    @Override
    public Optional<Pack> readNextRowFromDataSource() throws APIException {

        try {
            String currentLine = reader.readLine();

            //EOF
            if (currentLine == null || currentLine.length() == 0) {
                closeFile();
                return Optional.empty();
            }

            return Optional.of(dataParserImplCustomized.parseLine(currentLine));

        } catch (IOException e) {
            log.error("exception in reading from file", e);
            throw new APIException(ExceptionCodes.READ_FILE_EXP, e);
        }


    }


}
