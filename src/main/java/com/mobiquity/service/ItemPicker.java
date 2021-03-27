package com.mobiquity.service;

import com.mobiquity.domain.Item;

import java.math.BigDecimal;
import java.util.List;

public interface ItemPicker {
    List<Integer> pickItems(Integer capacity, int[] weight, int[] prices);
}
