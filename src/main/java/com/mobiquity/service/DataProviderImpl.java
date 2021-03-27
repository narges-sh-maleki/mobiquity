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

@RequiredArgsConstructor
@Slf4j
public class DataProviderImpl implements DataProvider {


    private final BufferedReader reader;
    private static final Pattern itemPattern = Pattern.compile("(\\((\\d+),([0-9]*\\.?[0-9]*),€(\\d+)\\))");


    public DataProviderImpl(String filePath) throws APIException {

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

            return parseLine(currentLine);

        } catch (IOException e) {
            log.error("exception in reading from file", e);
            throw new APIException(ExceptionCodes.READ_FILE_EXP, e);
        }


    }

    private Pack parseLine(String lineData) throws APIException {
        try {

            //split capacity and items
            String[] capacityItemArray = lineData.split(":");
            Pack.PackBuilder packBuilder = Pack.builder();
            packBuilder.capacity(Integer.valueOf(capacityItemArray[0].trim()));

            //parse items
            List<Item> items = parseItems(capacityItemArray[1]);

            packBuilder.possibleItems(items);
            return packBuilder.build();
        }
        catch (RuntimeException e){
            log.error("exception file format", e);
            throw new APIException(ExceptionCodes.FILE_FORMAT_EXP, e);
        }

    }

    private List<Item> parseItems(String input) {
        List<Item> items = new ArrayList<>();
        Matcher itemMatcher = itemPattern.matcher(input);
        while (itemMatcher.find()) {
            items.add(Item.builder().index(Integer.valueOf(itemMatcher.group(2)))
                    .weight(new BigDecimal(itemMatcher.group(3)))
                    .price(new BigDecimal(itemMatcher.group(4)))
                    .build());
        }
        return items;
    }


}
