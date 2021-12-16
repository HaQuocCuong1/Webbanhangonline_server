
package com.se.webbanhang.dto.request;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO {
 
    private int id;
    
    
    private String code;
    
    
    private String name;
    
    private Date date;
    
    private String logo;
    
}
