package com.mobiquity.domain;


import com.mobiquity.exception.APIException;
import com.mobiquity.exception.ExceptionCodes;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;



@Value
public class Pack {

    @Builder
    public Pack(@NonNull Integer capacity, @NonNull List<Item> possibleItems) throws APIException {
        if (capacity > 100 )
            throw new APIException(ExceptionCodes.FIELD_FORMAT_EXP , "capacity: " + capacity.toString());
        if ( possibleItems.size() > 15)
            throw new APIException(ExceptionCodes.FIELD_FORMAT_EXP , "item number: " + possibleItems.size());
        this.capacity = capacity;
        this.possibleItems = possibleItems;
    }

    @NonNull
    private final Integer capacity;
    @NonNull
    private final List<Item> possibleItems;

    public List<BigDecimal> getItemsWeight(){
        return this.possibleItems.stream().map(item -> item.getWeight()).collect(Collectors.toList());
    }

    public List<BigDecimal> getItemsPrice(){
        return this.possibleItems.stream().map(item -> item.getPrice()).collect(Collectors.toList());
    }

}
