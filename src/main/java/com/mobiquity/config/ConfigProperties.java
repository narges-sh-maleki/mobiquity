package com.mobiquity.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

@Slf4j
public class ConfigProperties {
    private static final String DEFAULT_NUMBER_PRECISION = "2";
    private static final String DEFAULT_COEFFICIENT = "100";
    private static final String DEFAULT_MAX_CAPACITY = "100";
    private static final String DEFAULT_MAX_ITEM_SIZE = "15";
    private static ConfigProperties configPropertiesInstance = new ConfigProperties();
    @Getter
    private final Integer numberPrecision;
    @Getter
    private final Integer coefficient;
    @Getter
    private final Integer maxCapacity;
    @Getter
    private final Integer maxItemSize;

    private ConfigProperties() {
        Integer numberPrecision = Integer.valueOf(DEFAULT_NUMBER_PRECISION);
        Integer coefficient = Integer.valueOf(DEFAULT_COEFFICIENT);
        Integer maxCapacity = Integer.valueOf(DEFAULT_MAX_CAPACITY);
        Integer maxItemSize = Integer.valueOf(DEFAULT_MAX_ITEM_SIZE);

        try {
            Properties appProps = new Properties();
            URL configUrl = ConfigProperties.class.getResource("/application.properties");
            if (configUrl != null) {

                appProps.load(new FileInputStream(configUrl.toURI().getPath()));

                numberPrecision = Integer.valueOf(appProps.getProperty("numberPrecision", DEFAULT_NUMBER_PRECISION));
                coefficient = Integer.valueOf(appProps.getProperty("coefficient", DEFAULT_COEFFICIENT));
                maxCapacity = Integer.valueOf(appProps.getProperty("maxCapacity", DEFAULT_MAX_CAPACITY));
                maxItemSize = Integer.valueOf(appProps.getProperty("maxItemSize", DEFAULT_MAX_ITEM_SIZE));
            }

        } catch (IOException | URISyntaxException e) {
            log.error("Exception reading properties file", e);

        }

        this.numberPrecision = numberPrecision;
        this.coefficient = coefficient;
        this.maxCapacity = maxCapacity;
        this.maxItemSize = maxItemSize;
    }


    public static ConfigProperties getInstance() {
        return configPropertiesInstance;
    }
}
