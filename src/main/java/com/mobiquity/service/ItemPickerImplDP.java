package com.mobiquity.service;

import com.mobiquity.exception.APIException;
import com.mobiquity.exception.ExceptionCodes;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public  class ItemPickerImplDP implements ItemPicker {
    @Override
    public  List<Integer> pickItems(Integer capacity, int[] weight, int[] cost) throws APIException {

        validateData(capacity, weight, cost);

        int[][] DP = fillDpTable(capacity, weight, cost);

        return prepareDataInProperOrder(getSelectedItems(weight, DP, capacity));
    }

    private void validateData(Integer capacity, int[] weight, int[] cost) throws APIException {
        if (weight == null || cost == null || weight.length != cost.length || capacity < 0)
        {
            log.error("Knapsack algo validation exception");
            throw new APIException(ExceptionCodes.FIELD_FORMAT_EXP,"knapsack Algorithm validation");
    }}

    private int[][] fillDpTable(Integer capacity, int[] weight, int[] cost) {
        //Dynamic Programming matrix for memorizing the previous calculations
        //rows: items
        //columns: weight of knapsack
        int[][] DP = new int[weight.length + 1][capacity + 1];

        for (int i = 1; i <= weight.length; i++) {
            fillDpRow(capacity, weight[i - 1], cost[i - 1], DP, i);
        }
        return DP;
    }

    private List<Integer> prepareDataInProperOrder(List<Integer> itemsSelected) {
        // Return the items that were selected
        java.util.Collections.reverse(itemsSelected);
        return itemsSelected;
    }

    private List<Integer> getSelectedItems(int[] weight, int[][] DP, int sz) {
        List<Integer> itemsSelected = new ArrayList<>();
        // checking the matrix to find selected items
        //  if DP[i][sz] != DP[i-1][sz] then the item was included
        for (int i = weight.length; i > 0; i--) {
            if (DP[i][sz] != DP[i - 1][sz]) {
                itemsSelected.add(i);
                sz -= weight[i-1];
            }
        }
        return itemsSelected;
    }

    private void fillDpRow(Integer capacity, int itemWeight, int itemPrice, int[][] DP, int i) {


        for (int sz = 1; sz <= capacity; sz++) {

            // excluding the item
            DP[i][sz] = DP[i - 1][sz];

            // Deciding to include or exclude the current item
            if (sz >= itemWeight && DP[i - 1][sz - itemWeight] + itemPrice > DP[i][sz])
                DP[i][sz] = DP[i - 1][sz - itemWeight] + itemPrice;
        }
    }
}
