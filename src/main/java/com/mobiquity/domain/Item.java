package com.mobiquity.domain;

import com.mobiquity.config.ConfigProperties;
import com.mobiquity.exception.APIException;
import com.mobiquity.exception.ExceptionCodes;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Value

public class Item {

    private ConfigProperties config = ConfigProperties.getInstance();

    @Builder
    public Item(@NonNull Integer index, @NonNull BigDecimal weight, @NonNull BigDecimal price) throws APIException {

        //validate fields
        ConfigProperties configuration = ConfigProperties.getInstance();
        if (weight.compareTo(new BigDecimal(configuration.getCoefficient())) > 0)
            throw new APIException(ExceptionCodes.FIELD_FORMAT_EXP, "weight: " + weight);
        if (price.compareTo(new BigDecimal(configuration.getCoefficient())) > 0)
            throw new APIException(ExceptionCodes.FIELD_FORMAT_EXP, "price: " + price);

        this.index = index;
        this.weight = weight.setScale(config.getNumberPrecision(), RoundingMode.CEILING);
        this.price = price.setScale(config.getNumberPrecision(), RoundingMode.CEILING);
    }


    @NonNull
    private final Integer index;
    @NonNull
    private final BigDecimal weight;
    @NonNull
    private final BigDecimal price;


}
