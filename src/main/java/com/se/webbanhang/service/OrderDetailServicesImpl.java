/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.service;

import com.se.webbanhang.dto.request.OrderDetailDTO;
import com.se.webbanhang.entity.Order_detail;
import com.se.webbanhang.entity.Orders;
import com.se.webbanhang.entity.Products;
import com.se.webbanhang.entity.Users;
import com.se.webbanhang.exception.ApiRequestException;
import com.se.webbanhang.exception.NotFoundException;
import com.se.webbanhang.repository.OrderDetailRespository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class OrderDetailServicesImpl implements OrderDetailService{
    @Autowired
    private OrderDetailRespository orderDetailRespository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UsersService usersService;

    @Override
    public List<Order_detail> findAll() {
         return orderDetailRespository.findAll();
    }

    @Override
    public Order_detail findbyId(int id) {
        Optional<Order_detail> result = orderDetailRespository.findById(id);
        Order_detail theOrderDetail = null;
        if (result.isPresent())
        {
            theOrderDetail = result.get();
        }else {
            throw new NotFoundException("Did not find Orderdetail id: "+id);
        }
        return theOrderDetail;
    }

    @Override
    public void save(Order_detail theOrderDetail) {
        orderDetailRespository.save(theOrderDetail);
    }

    @Override
    public void delete(int id) {
        Order_detail od = findbyId(id);
        if(od.getStatus() == 0)
            orderDetailRespository.deleteById(id);
        else
            throw new ApiRequestException("This product cannot be deleted due to processing");
    }

    @Override
    public List<Order_detail> getOrderDetailByOrderId(int orderId) {
        Orders theOrder = orderService.findbyId(orderId);
        List<Order_detail> listOrderDetail = null;
        if(theOrder != null)
        {
            listOrderDetail = theOrder.getListOrderDetail();
        }
        return listOrderDetail;
    }
    @Override
    public List<Order_detail> getOrderDetailByUserId(int userId) {
       Users theUser = usersService.findbyId(userId);
       List<Order_detail> listOrderDetail = null;
       List<Order_detail> listOrderDetai2 = new ArrayList<>();
       if(theUser != null)
       {
           List<Products> listProduct = theUser.getProducts();
           for(Products theProducts : listProduct)
           {
               listOrderDetail = theProducts.getListOrderDetail();
               if(listOrderDetail != null)
               {
                    for(Order_detail od : listOrderDetail)
                    {
                        if(od != null)
                        {
                            listOrderDetai2.add(od);
                        }
                    }
               }
           }
                  
       }
       return listOrderDetai2;
    }

    @Override
    public boolean updateStatusOrderDetail(int orderDetailId, int type) {
       Order_detail theOrderDetail = findbyId(orderDetailId);
       if(theOrderDetail == null)
           return false;
       else
       {
           orderDetailRespository.updateStatusOrderDetail(theOrderDetail.getId(), type);
           return true;
       }
    }

    @Override
    public List<Order_detail> getOrderDetailByStatus(int userId, int status) {
       List<Order_detail> listOrderDetai1 = getOrderDetailByUserId(userId);
       List<Order_detail> listOrderDetai2 = new ArrayList<Order_detail>();
       for(Order_detail od : listOrderDetai1)
       {
           if(od.getStatus() == status)
           {
               listOrderDetai2.add(od);
           }
       }
       return listOrderDetai2;
    }

    @Override
    public Integer getorderDetailConfirm(int userId, int status) {
        List<Order_detail> listOrderDetai1 = getOrderDetailByUserId(userId);
        int count = 0;
        for(Order_detail od : listOrderDetai1)
        {
            if(od.getStatus() == status)
            {
                count++;
            }
        }
        return count;
    }
}
