
package com.se.webbanhang.exception;

import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Admin
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiException {
    private String message;
    private Throwable throwable;
    private HttpStatus htpHttpStatus;
    private ZonedDateTime timestamp;

    
}
