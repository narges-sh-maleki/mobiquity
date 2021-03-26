package com.mobiquity.exception;

import lombok.Getter;

@Getter
public enum ExceptionCodes {
    EOF("End of File Exception"),
    FILE_FORMAT_EXP("Exception in file format"),
    READ_FILE_EXP("exception in reading from file");


    private final String exceptionMessage;
    ExceptionCodes(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
