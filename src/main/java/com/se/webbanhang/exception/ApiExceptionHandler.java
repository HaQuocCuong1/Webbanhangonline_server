
package com.se.webbanhang.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Admin
 */
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ApiException> handleException(NotFoundException exc) {

            // create CustomerErrorResponse

            ApiException error = new ApiException(
            exc.getMessage(),exc,
            HttpStatus.NOT_FOUND,
            ZonedDateTime.now(ZoneId.of("Z")));

            // return ResponseEntity

            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiResponseEntity(ApiRequestException e)
    {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
       ApiException apiException = new ApiException(e.getMessage(), e, HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
       return new ResponseEntity<>(apiException, badRequest);
    }
    
}
