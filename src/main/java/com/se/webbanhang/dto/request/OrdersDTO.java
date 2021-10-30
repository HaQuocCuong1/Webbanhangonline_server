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
 * @author ASUS
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
    private int id;
    
    private int payments;
   
    private String deliveryaddress;
    
    private double transportfee;
    
    private String namecustomer;
    
    private String email;
    
    private String phone;
    
    private String note;
    
    private int userId;
    
    
    public List<OrderDetailDTO> listOrderdetail;
    
}
