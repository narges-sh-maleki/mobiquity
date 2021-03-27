package com.mobiquity.service;

import com.mobiquity.domain.Pack;
import com.mobiquity.exception.APIException;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class PackerService {
    private final DataProvider dataProvider;
    private final ItemPicker itemPicker;

    public  String pack() throws APIException {

        String result="";
        while(true) {
            Pack pack = dataProvider.readOneRowFromDataSource();
            if (pack == null)
                break;
            List<Integer> itemIndexes = itemPicker.pickItems(pack.getCapacity() * 100,
                    pack.getItemsWeight().stream().mapToInt(weight -> weight.multiply(new BigDecimal(100)).intValue()).toArray(),
                    pack.getItemsPrice().stream().mapToInt(price -> price.intValue()).toArray()
            );

            result+= itemIndexes.toString() + "\n";
        }
        return result;
    }




}
