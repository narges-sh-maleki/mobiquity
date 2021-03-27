package com.mobiquity.service;

import java.util.ArrayList;
import java.util.List;

public  class ItemPickerImpl implements ItemPicker {
    @Override
    public  List<Integer> pickItems(Integer capacity, int[] weight, int[] cost) {
        if (weight == null || cost == null || weight.length != cost.length || capacity < 0)
            throw new IllegalArgumentException("Invalid input");



        //Dynamic Programming matrix for memorizing the previous calculations
        //rows: items
        //columns: weight of knapsack
        int[][] DP = new int[weight.length + 1][capacity + 1];

        for (int i = 1; i <= weight.length; i++) {


            int itemWeight = weight[i - 1];
            int itemPrice = cost[i - 1];

            for (int sz = 1; sz <= capacity; sz++) {

                // not picking the item
                DP[i][sz] = DP[i - 1][sz];

                // Deciding to include or exclude the current item
                if (sz >= itemWeight && DP[i - 1][sz - itemWeight] + itemPrice > DP[i][sz])
                    DP[i][sz] = DP[i - 1][sz - itemWeight] + itemPrice;

            }
        }

        int sz = capacity;
        List<Integer> itemsSelected = new ArrayList<>();

        // Using the information inside the table we can backtrack and determine
        // which items were selected during the dynamic programming phase. The idea
        // is that if DP[i][sz] != DP[i-1][sz] then the item was selected
        for (int i = weight.length; i > 0; i--) {
            if (DP[i][sz] != DP[i - 1][sz]) {

                itemsSelected.add(i);
                sz -= weight[i-1];
            }
        }

        // Return the items that were selected
        java.util.Collections.reverse(itemsSelected);
        return itemsSelected;

        // Return the maximum profit
        //return DP[N][capacity];
    }
}
