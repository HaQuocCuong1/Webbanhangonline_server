/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.service;

import com.se.webbanhang.entity.Orders;
import java.sql.Date;
import java.util.List;
import java.util.Optional;


public interface OrderService {
    public List<Orders> findAll();
   // public Orders findByDate_order(Date dateOrder);
    public Orders findbyId(int id);
    public void save(Orders theOrders);
    public void delete(int id);
    public boolean updateStatusOrder(int id);
}
