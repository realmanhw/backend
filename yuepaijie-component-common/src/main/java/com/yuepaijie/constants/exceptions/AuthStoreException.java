/**
 * TigerBrokers
 * Copyright (C) 2014-2019 All Rights Reserved.
 */
package com.yuepaijie.constants.exceptions;

/**
 * @author luojiaqi 2019/03/15
 */
public class AuthStoreException extends RuntimeException {

  public int errorCode;

  public AuthStoreException(String message) {
    super(message);
  }

  public AuthStoreException(Throwable cause) {
    super(cause);
  }

  public AuthStoreException(String message, Throwable cause) {
    super(message, cause);
  }
}
