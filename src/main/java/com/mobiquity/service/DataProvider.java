package com.mobiquity.service;

import com.mobiquity.domain.Pack;
import com.mobiquity.exception.APIException;

public interface DataProvider {
    Pack readOneRowFromDataSource() throws APIException;
}
