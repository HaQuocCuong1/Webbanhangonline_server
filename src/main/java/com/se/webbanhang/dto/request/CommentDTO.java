
package com.se.webbanhang.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Setter
@Getter
public class CommentDTO {
    
    private int id;
    
    
    private String name;
    
    private String avatar;
   
    private String content;
    
   
    private int userId;
    
    
    private int productId;
}
