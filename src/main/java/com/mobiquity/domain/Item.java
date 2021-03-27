package com.mobiquity.domain;

import com.mobiquity.exception.APIException;
import com.mobiquity.exception.ExceptionCodes;
import lombok.*;

import java.math.BigDecimal;

@Value


public class Item {

    @Builder
    public Item(@NonNull Integer index, @NonNull BigDecimal weight, @NonNull BigDecimal price) throws APIException {
        this.index = index;
        if (weight.compareTo(new BigDecimal(100))>0)
            throw new APIException(ExceptionCodes.FIELD_FORMAT_EXP,"weight: " + weight);
        if (price.compareTo(new BigDecimal(100))>0)
            throw new APIException(ExceptionCodes.FIELD_FORMAT_EXP,"price: " + price);

        this.weight = weight;
        this.price = price;
    }

    @NonNull
    private final Integer index;
    @NonNull
    private final BigDecimal weight;
    @NonNull
    private final BigDecimal price;


}
