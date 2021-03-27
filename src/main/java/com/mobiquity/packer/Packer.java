package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.service.*;


public class Packer {


  private Packer(){

  }

  public static String pack(String filePath) throws APIException {

    PackerService packerService = new PackerService(new DataProviderImpl(filePath,new DataParser()), new ItemPickerImpl());

    return packerService.pack();
  }

}
