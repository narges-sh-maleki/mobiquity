package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.repository.DataParserImplCustomized;
import com.mobiquity.repository.DataProviderImplFileReader;
import com.mobiquity.service.ItemPickerImplDP;


public class Packer {


    private Packer() {

    }

    public static String pack(String filePath) throws APIException {
        return initClasses(filePath).pack();
    }

    private static PackerService initClasses(String filePath) throws APIException {
        return new PackerService(new DataProviderImplFileReader(filePath, new DataParserImplCustomized()), new ItemPickerImplDP());
    }

}
