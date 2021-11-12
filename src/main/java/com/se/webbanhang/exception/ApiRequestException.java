
package com.se.webbanhang.exception;

/**
 *
 * @author Admin
 */
public class ApiRequestException extends RuntimeException{

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable thrwbl) {
        super(message, thrwbl);
    }
    
}
