package com.mobiquity.domain;


import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Builder
@Value
public class Pack {
    @NonNull
    private final BigDecimal capacity;
    @NonNull
    private final List<Item> possibleItems;

}
