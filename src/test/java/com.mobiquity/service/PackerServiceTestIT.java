package com.mobiquity.service;

import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackerServiceTestIT {

    PackerService packerService ;
    @Test
    void pack() throws APIException {
        //given
        String path = DataProviderImplTest.class.getResource("/example_input").getPath();
        packerService = new PackerService(new DataProviderImpl(path, new DataParser()),
                new ItemPickerImpl());
        //when
        String result = packerService.pack();

        //then
        System.out.println(result);
    }
}