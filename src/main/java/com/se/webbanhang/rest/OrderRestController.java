/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.rest;

import com.se.webbanhang.dto.request.OrderDetailDTO;
import com.se.webbanhang.dto.request.OrdersDTO;
import com.se.webbanhang.entity.Order_detail;
import com.se.webbanhang.entity.Orders;
import com.se.webbanhang.entity.Products;
import com.se.webbanhang.entity.Users;
import com.se.webbanhang.exception.ApiRequestException;
import com.se.webbanhang.exception.NotFoundException;
import com.se.webbanhang.service.OrderDetailService;
import com.se.webbanhang.service.OrderService;
import com.se.webbanhang.service.ProductService;
import com.se.webbanhang.service.UsersService;
import io.jsonwebtoken.Claims;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class OrderRestController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private ProductService productService;
    
    @Autowired
    private OrderDetailService orderDetailService;
    
    @GetMapping("/orders")
    public List<Orders> findAll()
    {
        return orderService.findAll();
    }
    @GetMapping("/orders/users/{userId}")
    public List<Orders> findAllByUser(@PathVariable int userId)
    {
        Users theUsers = usersService.findbyId(userId);
        List<Orders> listOrder = null;
        if (theUsers != null)
        {
            listOrder = theUsers.getListorders();
        }else{
            throw new NotFoundException("user not found userId "+userId);
        }
        return listOrder;
    }
    @GetMapping("/orders/{orderId}")
    public Orders findByid(@PathVariable int orderId)
    {
        Orders theorder = orderService.findbyId(orderId);
        if (theorder == null)
        {
            throw new NotFoundException("Order not find by "+ orderId);
        }
        return theorder;
    }
    @PostMapping("/orders")
    public String save(@RequestBody OrdersDTO theOrders)
    {
        try {
            
            theOrders.setId(0);
            long millis=System.currentTimeMillis();
            Orders theOrder = new Orders(new Date(millis), theOrders.getPayments(), theOrders.getDeliveryaddress(), theOrders.getTransportfee(), theOrders.getNamecustomer(), theOrders.getEmail(), theOrders.getPhone(), theOrders.getNote());
            Users theUsers = usersService.findbyId(theOrders.getUserId());
            theOrder.setUsers(theUsers);
            
            List<Order_detail> listDetail = new ArrayList<>();
            List<OrderDetailDTO> listOD = theOrders.getListOrderdetail();
            Products theProducts1 = null;
            int count = 0;
            int quantityOrderDetail = 0;
            int quantityOrder = 0;
            for(OrderDetailDTO od : listOD)
            {
                Order_detail orderdtail = new Order_detail();
                orderdtail.setDiscount(od.getDiscount());
                orderdtail.setQuantity(od.getQuantity());
                orderdtail.setTotalmoney(od.getTotalmoney());
                orderdtail.setOrders(theOrder);
                orderdtail.setAvartar(od.getAvartar());
                orderdtail.setName(od.getName());
                
                theProducts1 = this.productService.findById(od.getProductId());
                orderdtail.setProducts(theProducts1);
                listDetail.add(orderdtail);
                count = od.getQuantity();
                if (quantityOrderDetail == 0)
                {
                    for (Order_detail ods : theProducts1.getListOrderDetail())
                    {
                    
                        if (ods != null)
                        {
                            int quantity = ods.getQuantity();
                            quantityOrderDetail = quantityOrderDetail+ quantity;
                        }
                    }
                }
                if (quantityOrder == 0)
                {
                    quantityOrder = count + quantityOrderDetail;
                }else {
                    quantityOrder = quantityOrder + count;
                }
                productService.updateBannhan(od.getProductId(), quantityOrder);
            }
            theOrder.setListOrderDetail(listDetail);
            orderService.save(theOrder);
            return "Payment success";
        }catch(Exception e)
        {
            throw new RuntimeException("Payment Fail ",e);
        }
    }
    @PutMapping("/orders")
    public Orders update(@RequestBody OrdersDTO theOrders)
    {
        try {
            long millis=System.currentTimeMillis();
            
            Orders theOrder = new Orders(new Date(millis), theOrders.getPayments(), theOrders.getDeliveryaddress(), theOrders.getTransportfee(), theOrders.getNamecustomer(), theOrders.getEmail(), theOrders.getPhone(), theOrders.getNote());
            Users theUsers = usersService.findbyId(theOrders.getUserId());
            theOrder.setUsers(theUsers);
            orderService.save(theOrder);
            
            return theOrder;
        }catch(Exception e)
        {
            throw new ApiRequestException("Update order fail ", e);
        }
    }
    @DeleteMapping("/orders/{orderId}")
    public String delete (@PathVariable int orderId)
    {
        Orders tempOrders = orderService.findbyId(orderId);
        if (tempOrders == null)
        {
            throw new NotFoundException("Categories id not found - " + orderId);
        } else {
            orderService.delete(orderId);
        }
        return "Delete order Id: "+orderId;
    }
    //Verifi order
    @PostMapping("/verify/orders/{orderId}")
    public String verifyOrders(@PathVariable int orderId)
    {
        boolean checkupdate = orderService.updateStatusOrder(orderId);
        if(checkupdate == true)
            return "Update status order susscess!";
        else
            return "Update status order fail!";
    }
}
