package com.mobiquity.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class ConfigProperties {
    private static final String DEFAULT_NUMBER_PRECISION = "2";
    private static final String DEFAULT_COEFFICIENT = "100";
    private static ConfigProperties configPropertiesInstance = new ConfigProperties();
    @Getter
    private final Integer numberPrecision;
    @Getter
    private final Integer coefficient;

    private ConfigProperties() {
        Integer numberPrecision;
        Integer coefficient;
        try {
            String rootPath = ConfigProperties.class.getResource("/application.properties").getPath();
            Properties appProps = new Properties();
            appProps.load(new FileInputStream(rootPath));

            numberPrecision = Integer.valueOf(appProps.getProperty("NumberPrecision", DEFAULT_NUMBER_PRECISION));
            coefficient = Integer.valueOf(appProps.getProperty("Coefficient", DEFAULT_COEFFICIENT));
        } catch (IOException e) {
            log.error("Exception reading properties file", e);
            numberPrecision = Integer.valueOf(DEFAULT_NUMBER_PRECISION);
            coefficient = Integer.valueOf(DEFAULT_COEFFICIENT);
        }

        this.numberPrecision = numberPrecision;
        this.coefficient = coefficient;

    }


    public static ConfigProperties getInstance() {
        return configPropertiesInstance;
    }
}
