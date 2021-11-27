/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.rest;

import com.se.webbanhang.dto.request.ListRevenueDTO;
import com.se.webbanhang.dto.request.OrderDetailDTO;
import com.se.webbanhang.entity.Order_detail;
import com.se.webbanhang.service.OrderDetailService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    private List<Order_detail> listOrderDetails = new ArrayList<>();
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
    @GetMapping("/orderdetails/users/{userId}")
    public List<Order_detail> getOrderDetailByUser(@PathVariable int userId)
    {
        return orderDetailService.getOrderDetailByUserId(userId);
    }
    @GetMapping("/orderdetails/users/{userId}/status/{status}")
    public List<Order_detail> getOrderDetailByStatus(@PathVariable int userId, @PathVariable int status)
    {
        return orderDetailService.getOrderDetailByStatus(userId, status);
    }
    @GetMapping("/orderdetails/confirm/users/{userId}/status/{status}")
    public Integer orderDetailConfirm(@PathVariable int userId, @PathVariable int status) {
        // get persons from the service
        return orderDetailService.getorderDetailConfirm(userId, status); 
    }
    @GetMapping("/totalrevenue/user/{userId}")
    public Double totalRevenue(@PathVariable int userId) {
        // get persons from the service
        return orderDetailService.gettotalRevenue(userId); 
    }
    @GetMapping("/totalorderdetail/month/user/{userId}")
    public Integer totalOrderdetailByMonth(@PathVariable int userId) {
        // get persons from the service
        return orderDetailService.getTotalOrderdetailByMonth(userId); 
    }
    @GetMapping("/totalrevenue/month/user/{userId}")
    public Double totalrevenueByMonth(@PathVariable int userId) {
        // get persons from the service
        return orderDetailService.getTotalrevenueByMonth(userId); 
    }
    @GetMapping("/totalorder/month/user/{userId}")
    public Integer totalorderByMonth(@PathVariable int userId) {
        // get persons from the service
        return orderDetailService.totalorderByMonth(userId); 
    }
    @GetMapping("/totalrevenue/fromdate/{fromdate}/todate/{todate}/user/{userId}")
    public Double totalrevenueByFromDateAndToDate(@PathVariable int userId, @PathVariable Date fromdate, @PathVariable Date todate) {
        // get persons from the service
        return orderDetailService.getTotalrevenueByFromDateAndToDate(userId, fromdate, todate); 
    }
    @GetMapping("/totalorder/fromdate/{fromdate}/todate/{todate}/user/{userId}")
    public Integer totalorderByFromDateAndToDate(@PathVariable int userId, @PathVariable Date fromdate, @PathVariable Date todate) {
        // get persons from the service
        return orderDetailService.totalorderByFromDateAndToDate(userId, fromdate, todate); 
    }
    @GetMapping("/revenue/user/{userId}")
    public ListRevenueDTO RevenueChart(@PathVariable int userId) {
        // get persons from the service
        return orderDetailService.revenueChart(userId); 
    }
    @PostMapping("/status/orderDetail/{orderDetailId}/type/{type}")
    public String updateFeatured(@PathVariable int orderDetailId, @PathVariable int type)
    {
        boolean checkupdate = orderDetailService.updateStatusOrderDetail(orderDetailId, type);
        if(checkupdate == true)
            return "Confirm order success!";
        else
            return "Confirm order fail!";
    }
    @DeleteMapping("/orderdetails/{orderDetailId}/type/{type}")
    public String deteleOrdertail(@PathVariable int orderDetailId, @PathVariable int type)
    {  
        orderDetailService.delete(orderDetailId, type);
        return "Delete orderDetail Id "+orderDetailId;
    }
}
