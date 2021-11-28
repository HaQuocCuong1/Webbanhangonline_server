/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.service;

import com.se.webbanhang.dto.request.ListRevenueDTO;
import com.se.webbanhang.dto.request.OrderDetailDTO;
import com.se.webbanhang.dto.request.RevenueDTO;
import com.se.webbanhang.entity.Order_detail;
import com.se.webbanhang.entity.Orders;
import com.se.webbanhang.entity.Products;
import com.se.webbanhang.entity.Users;
import com.se.webbanhang.exception.ApiRequestException;
import com.se.webbanhang.exception.NotFoundException;
import com.se.webbanhang.repository.OrderDetailRespository;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
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
    public void delete(int id, int type) {
        Order_detail od = findbyId(id);
        if(od.getStatus() == type)
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

    @Override
    public Double gettotalRevenue(int userId) {
        List<Order_detail> listod = getOrderDetailByUserId(userId);
        double totalrevenue = 0;
        for(Order_detail od : listod)
        {
            totalrevenue += od.getTotalmoney();
        }
        return totalrevenue;
    }

    @Override
    public Integer getTotalOrderdetailByMonth(int userId) {
        LocalDate localDate = LocalDate.now();
        Month month = localDate.getMonth();
        int count = orderDetailRespository.totalOrderdetailByMonth(userId, month.getValue());
        return count;
    }

    @Override
    public Double getTotalrevenueByMonth(int userId) {
        double total = 0;
        LocalDate localDate = LocalDate.now();
        Month month = localDate.getMonth();
        Users theUsers = usersService.findbyId(userId);
        if(theUsers != null)
        {
            if(theUsers.getProducts().isEmpty())
                throw new ApiRequestException("User not products !");
            else
                total = orderDetailRespository.totalrevenueByMonth(theUsers.getId(), month.getValue());
        }else{
            throw new NotFoundException("Not found User Id: "+userId);
        }
        return total;
    }

    @Override
    public Double getTotalrevenueByFromDateAndToDate(int userId, Date fromdate, Date todate) {
        double total = 0;
        Users theUsers = usersService.findbyId(userId);
        if(theUsers != null)
        {
            if(theUsers.getProducts().isEmpty())
                throw new ApiRequestException("User not products !");
            else
                total = orderDetailRespository.getTotalrevenueByFromDateAndToDate(theUsers.getId(), fromdate, todate);
        }else{
            throw new NotFoundException("Not found User Id: "+userId);
        }
        return total;
    }

    @Override
    public Integer totalorderByMonth(int userId) {
        LocalDate localDate = LocalDate.now();
        Month month = localDate.getMonth();
        int count = orderDetailRespository.totalorderByMonth(userId, month.getValue());
        return count;
    }

    @Override
    public Integer totalorderByFromDateAndToDate(int userId, Date fromdate, Date todate) {
        int total = orderDetailRespository.getTotalOrderByFromDateAndToDate(userId, fromdate, todate);
        return total;
    }

    @Override
    public ListRevenueDTO revenueChart(int userId) {
        Users theUsers = usersService.findbyId(userId);
        List<RevenueDTO> revenues = new ArrayList<>();
        ListRevenueDTO listRevenueDTO = new ListRevenueDTO();
        if(theUsers != null)
        {
            revenues = orderDetailRespository.getRevenueByUserId(userId);
            listRevenueDTO.setThongke(revenues);
        }else{
            throw new NotFoundException("Not found User Id: "+userId);
        }
        return listRevenueDTO;
    }
}
