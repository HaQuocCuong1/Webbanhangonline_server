/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.service;

import com.se.webbanhang.entity.Order_detail;
import com.se.webbanhang.entity.Orders;
import com.se.webbanhang.repository.UsersRespository;
import com.se.webbanhang.entity.Role_user;
import com.se.webbanhang.entity.Store;
import com.se.webbanhang.entity.Users;
import com.se.webbanhang.exception.NotFoundException;
import com.se.webbanhang.service.RoleUserService;
import com.se.webbanhang.service.UsersService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class UsersServiceImpl implements UsersService{
    @Autowired
    private UsersRespository usersRespository;
    @Autowired
    private RoleUserService roleUserService;
    @Autowired
    private StoreService storeService;

    @Override
    public List<Users> findAll() {
        return usersRespository.findAll();
    }

    @Override
    public Users findbyId(int id) {
        Optional<Users> result = usersRespository.findById(id);
        Users theUsers = null;
        if (result.isPresent())
        {
            theUsers = result.get();
        }else {
            throw new NotFoundException("Did not find User id: "+id);
        }
        return theUsers;
    }

    @Override
    public void save(Users theUsers) {
        usersRespository.save(theUsers);
    }

    @Override
    public void delete(int id) {
        usersRespository.deleteById(id);
    }

    @Override
    public List<Role_user> getroles(int userId) {
        Users theUsers = usersRespository.getById(userId);
        List<Role_user> listRoles = null;
        if (theUsers != null)
        {
            listRoles = theUsers.getListroles();
        }
        return listRoles;
    }

    @Override
    public Role_user getRole(int userId, int roleId) {
        Users theUsers = usersRespository.getById(userId);
        if (theUsers != null)
            return theUsers.getRoleUser(roleId);
        else
            return null;
    }

    @Override
    public Optional<Users> findByUsername(String name) {
        return usersRespository.findByUsername(name);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return usersRespository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return usersRespository.existsByEmail(email);
    }

    @Override
    public boolean findByVerificationCode(String code) {
        Users theUsers = usersRespository.findByVerificationCode(code);
        if (theUsers == null)
            return false;
        else
        {
            usersRespository.enable(theUsers.getId());
            return true;
        }
            
    }

    @Override
    public Users findByStoreId(int storeId) {
        Store theStore = storeService.findbyId(storeId);
        Users theUsers = theStore.getUsers();
        return theUsers;
    }

    @Override
    public Integer totalUser(String type) {
        List<Users> users = findAll();
        int count = 0;
        for(Users u : users)
        {
            List<Role_user> roles = u.getListroles();
            for(Role_user role : roles)
            {
                if(role.getRole().equals(type))
                    count++;
            }
        }
        return count;
    }

    @Override
    public Double totalInvenue() {
        double total = 0;
        List<Users> userses = findAll();
        for(Users u : userses)
        {
            List<Role_user> roles = u.getListroles();
            for(Role_user role : roles)
            {
                if(role.getRole().equals("user") || role.getRole().equals("Buyer"))
                {
                    Users theUsers = role.getUsers();
                    List<Orders> listOrder = theUsers.getListorders();
                    for(Orders theOrders: listOrder)
                    {
                        List<Order_detail> od1 = theOrders.getListOrderDetail();
                        for (Order_detail od2 : od1)
                        {
                            total+=od2.getTotalmoney();
                        }
                    }
                }
            }
        }
        return total;
    }
    
}
