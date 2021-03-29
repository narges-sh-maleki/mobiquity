package com.mobiquity.service;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.PackerService;
import com.mobiquity.repository.DataParserImplCustomized;
import com.mobiquity.repository.DataProviderImplFileReader;
import org.junit.jupiter.api.Test;

class PackerServiceTestIT {

    PackerService packerService ;
    @Test
    void pack() throws APIException {
        //given
        String path = DataProviderImplFileReaderTest.class.getResource("/example_input").getPath();
        packerService = new PackerService(new DataProviderImplFileReader(path, new DataParserImplCustomized()),
                new ItemPickerImplDP());
        //when
        String result = packerService.pack();

        //then
        System.out.println(result);
    }
}