package com.mobiquity.repository;

import com.mobiquity.domain.Item;
import com.mobiquity.domain.Pack;
import com.mobiquity.exception.APIException;
import com.mobiquity.exception.ExceptionCodes;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class DataParserImplCustomized implements DataParser {

    private static final Pattern itemPattern = Pattern.compile("(\\((\\d+),([0-9]*\\.?[0-9]*),€(\\d+)\\))");

    @Override
    public Pack parseLine(String lineData) throws APIException {
        String[] capacityItemArray;
        Pack.PackBuilder packBuilder = Pack.builder();

        //split capacity and items
        capacityItemArray = lineData.split(" :");
        if (capacityItemArray.length != 2) {
            log.error("exception file format: " + lineData);
            throw new APIException(ExceptionCodes.FILE_FORMAT_EXP, lineData);
        }

        packBuilder.capacity(Integer.valueOf(capacityItemArray[0]));

        //parse items
        List<Item> items = parseItems(capacityItemArray[1]);

        packBuilder.possibleItems(items);
        return packBuilder.build();


    }

    private List<Item> parseItems(String input) throws APIException {

        List<Item> items = new ArrayList<>();
        try {
            Matcher itemMatcher = itemPattern.matcher(input);
            while (itemMatcher.find()) {
                items.add(Item.builder().index(Integer.valueOf(itemMatcher.group(2)))
                        .weight(new BigDecimal(itemMatcher.group(3)))
                        .price(new BigDecimal(itemMatcher.group(4)))
                        .build());
            }
        }
        catch (RuntimeException e){
            log.error("exception in file format: " + input);
            throw new APIException(ExceptionCodes.FILE_FORMAT_EXP, input);
        }
        return items;
    }

}
