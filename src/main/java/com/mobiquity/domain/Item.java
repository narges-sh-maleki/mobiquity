package com.mobiquity.domain;

import lombok.*;

import java.math.BigDecimal;

@Value
@Builder

public class Item {

    @NonNull
    private final Integer index;
    @NonNull
    private final BigDecimal weight;
    @NonNull
    private final BigDecimal price;


}
