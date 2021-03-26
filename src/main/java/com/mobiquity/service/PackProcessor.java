package com.mobiquity.service;

import com.mobiquity.domain.Item;

import java.math.BigDecimal;
import java.util.List;

public interface PackProcessor {
    List<Integer> fillPackage(Integer capacity, int[] weight, int[] prices);
}
