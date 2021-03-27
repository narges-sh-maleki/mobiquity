package com.mobiquity.domain;


import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Builder
@Value
public class Pack {
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
