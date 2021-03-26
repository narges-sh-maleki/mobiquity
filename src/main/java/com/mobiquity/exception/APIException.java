package com.mobiquity.exception;

public class APIException extends Exception {

  private  ExceptionCodes exceptionCode;

  public APIException(String message, Exception e) {
    super(message, e);
  }

  public APIException(String message) {
    super(message);
  }


  public APIException(ExceptionCodes exceptionCode, Exception e) {
    super(e);
    this.exceptionCode = exceptionCode;
  }

  public APIException(ExceptionCodes exceptionCode){
    super(exceptionCode.getExceptionMessage());
    this.exceptionCode = exceptionCode;
  }

}
