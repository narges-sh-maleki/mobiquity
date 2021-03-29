package com.mobiquity.repository;

import com.mobiquity.domain.Pack;
import com.mobiquity.exception.APIException;

public interface DataParser {
    Pack parseLine(String lineData) throws APIException;
}
