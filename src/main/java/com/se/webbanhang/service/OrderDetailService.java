/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.service;

import com.se.webbanhang.dto.request.OrderDetailDTO;
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
    public List<Order_detail> getOrderDetailByOrderId(int orderId);
    public List<Order_detail> getOrderDetailByUserId(int userId);
    public boolean updateStatusOrderDetail(int orderDetailId, int type);
    public List<Order_detail> getOrderDetailByStatus(int userId, int status);
    public Integer getorderDetailConfirm(int userId, int status);
}
