package com.mobiquity.repository;

import com.mobiquity.domain.Pack;
import com.mobiquity.exception.APIException;

import java.util.Optional;

public interface DataProvider {
    Optional<Pack> readNextRowFromDataSource() throws APIException;
}
