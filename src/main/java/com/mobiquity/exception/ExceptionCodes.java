package com.mobiquity.exception;

import lombok.Getter;

@Getter
public enum ExceptionCodes {
    EOF("End of File Exception"),
    FILE_FORMAT_EXP("Exception in file format"),
    FIELD_FORMAT_EXP("Exception in field format"),
    CLOSE_FILE_EXP("Exception in closing file"),
    READ_FILE_EXP("exception in reading from file");


    private final String exceptionMessage;
    ExceptionCodes(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
