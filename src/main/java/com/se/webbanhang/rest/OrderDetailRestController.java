/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.rest;

import com.se.webbanhang.entity.Order_detail;
import com.se.webbanhang.service.OrderDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/api")
public class OrderDetailRestController {
    @Autowired
    private OrderDetailService orderDetailService;
   
    @GetMapping("/orderdetails")
    public List<Order_detail> findAll()
    {
        return orderDetailService.findAll();
    }
    @GetMapping("/orderdetails/orders/{orderId}")
    public List<Order_detail> getOrderDetailByOrderId(@PathVariable int orderId)
    {
        return orderDetailService.getOrderDetailByOrderId(orderId);
    }
}
