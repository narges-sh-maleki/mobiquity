package com.mobiquity.service;

import com.mobiquity.domain.Item;
import com.mobiquity.domain.Pack;
import com.mobiquity.exception.APIException;
import com.mobiquity.exception.ExceptionCodes;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
public class DataProviderImpl implements DataProvider {


    private final BufferedReader reader;
    private final DataParser dataParser;


    public DataProviderImpl(String filePath,DataParser dataParser) throws APIException {

        this.dataParser = dataParser;
        try {
            this.reader = new BufferedReader
                    (new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            log.error("exception in reading from file", e);
            throw new APIException("exception in reading file", e);
        }

    }


    @Override
    public Pack readOneRowFromDataSource() throws APIException {

        try {
            String currentLine = reader.readLine();
            //EOF
            if (currentLine == null || currentLine.length() == 0)
                return null;

            return dataParser.parseLine(currentLine);

        } catch (IOException e) {
            log.error("exception in reading from file", e);
            throw new APIException(ExceptionCodes.READ_FILE_EXP, e);
        }


    }


}
