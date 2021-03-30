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

    private List<Integer> getSelectedItems(int[] weight, int[][] DP, int capacity) {
        List<Integer> itemsSelected = new ArrayList<>();
        // checking the matrix to find selected items
        //  if DP[i][capacity] != DP[i-1][capacity] then the item was included
        for (int i = weight.length; i > 0; i--) {
            if (DP[i][capacity] != DP[i - 1][capacity]) {
                itemsSelected.add(i);
                capacity -= weight[i-1];
            }
        }
        return itemsSelected;
    }

    private void fillDpRow(Integer capacity, int itemWeight, int itemPrice, int[][] DP, int rowIndex) {


        for (int j = 1; j <= capacity; j++) {

            // excluding the item
            DP[rowIndex][j] = DP[rowIndex - 1][j];

            // Deciding to include or exclude the current item
            if (j >= itemWeight && DP[rowIndex - 1][j - itemWeight] + itemPrice > DP[rowIndex][j])
                DP[rowIndex][j] = DP[rowIndex - 1][j - itemWeight] + itemPrice;
        }
    }
}
