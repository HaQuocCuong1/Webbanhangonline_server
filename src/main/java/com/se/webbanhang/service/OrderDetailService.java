/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.service;

import com.se.webbanhang.entity.Order_detail;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface OrderDetailService {
    public List<Order_detail> findAll();
    public Order_detail findbyId(int id);
    public void save(Order_detail theOrderDetail);
    public void delete(int id);
}
