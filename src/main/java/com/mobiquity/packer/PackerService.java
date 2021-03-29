package com.mobiquity.packer;

import com.mobiquity.config.ConfigProperties;
import com.mobiquity.domain.Pack;
import com.mobiquity.exception.APIException;
import com.mobiquity.repository.DataProvider;
import com.mobiquity.service.ItemPicker;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PackerService {
    private final DataProvider dataProvider;
    private final ItemPicker itemPicker;
    private ConfigProperties config = ConfigProperties.getInstance();

    public String pack() throws APIException {

        StringBuilder result = new StringBuilder();
        while (true) {
            Optional<Pack> packOption = dataProvider.readNextRowFromDataSource();
            if (packOption.isPresent()) {
                Pack pack = packOption.get();
                List<Integer> itemIndexes = itemPicker.pickItems(pack.getCapacity() * config.getCoefficient(),
                        pack.getItemsWeight().stream().mapToInt(weight -> weight.multiply(new BigDecimal(config.getCoefficient())).intValue()).toArray(),
                        pack.getItemsPrice().stream().mapToInt(BigDecimal::intValue).toArray()
                );

                result.append(itemIndexes.toString()).append(System.lineSeparator());
            } else {
                break;
            }
        }
        return result.toString();
    }


}
