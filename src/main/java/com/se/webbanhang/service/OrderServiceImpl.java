/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.service;

import com.se.webbanhang.repository.OrderRespository;
import com.se.webbanhang.entity.Orders;
import com.se.webbanhang.entity.Users;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRespository oderRespository;
    @Autowired
    private UsersService usersService;
    
    @Override
    public List<Orders> findAll() {
        return oderRespository.findAll();
    }

    @Override
    public Orders findbyId(int id) {
        Optional<Orders> result = oderRespository.findById(id);
        Orders theOrders = null;
        if (result.isPresent())
        {
            theOrders = result.get();
        }else {
            throw new RuntimeException("Did not find Order id: "+id);
        }
        return theOrders;
    }

    @Override
    public void save(Orders theOrders) {
        oderRespository.save(theOrders);
    }

    @Override
    public void delete(int id) {
        oderRespository.deleteById(id);
    }

    @Override
    public boolean updateStatusOrder(int id) {
       Orders theOrders = findbyId(id);
       if(theOrders == null)
           return false;
       else
       {
           oderRespository.updateStatusOrder(theOrders.getId());
           return true;
       }
    }
}
