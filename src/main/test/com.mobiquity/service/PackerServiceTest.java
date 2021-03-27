package com.mobiquity.service;

import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackerServiceTestIT {

    PackerService packerService ;
    @Test
    void pack() throws APIException {
        //given
        packerService = new PackerService(new DataProviderImpl("src/main/test/resources/example_input"),
                new ItemPickerImpl());
        //when
        String result = packerService.pack();

        //then
        System.out.println(result);
    }
}