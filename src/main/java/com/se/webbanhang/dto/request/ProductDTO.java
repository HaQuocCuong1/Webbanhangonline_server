/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.webbanhang.dto.request;

import java.sql.Date;
import java.util.List;
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
public class ProductDTO {
    private int id;
    
    private String name;

    private String code;

    private String sort_description;

    private String detail_description;

    private double price;
    
    private int quantity;
    
    private int userId;

    private int categoriesId;

    private int suppliersId;
    
    private List<PictureProductDTO> listpictureProductDTOs;
}
