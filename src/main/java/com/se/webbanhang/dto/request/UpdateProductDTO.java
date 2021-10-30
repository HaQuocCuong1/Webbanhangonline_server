/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class UpdateProductDTO {
    private int id;
    
    private String name;

    private String code;

    private String sort_description;

    private String detail_description;

    private double price;
    
    private String avartar;
    
    private Date date_sale;
    
    private int quantity;
    
    private int promotion;
    
    private int userId;

    private int categoriesId;

    private int suppliersId;
    
    private List<PictureProductDTO> listpictureProductDTOs;
}
