package com.mobiquity.service;

import com.mobiquity.exception.APIException;

import java.util.List;

public interface ItemPicker {
    List<Integer> pickItems(Integer capacity, int[] weight, int[] prices) throws APIException;
}
