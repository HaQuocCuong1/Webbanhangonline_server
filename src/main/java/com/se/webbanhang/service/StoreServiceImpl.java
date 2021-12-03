/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se.webbanhang.service;

import com.se.webbanhang.entity.Products;
import com.se.webbanhang.entity.Store;
import com.se.webbanhang.entity.Users;
import com.se.webbanhang.exception.NotFoundException;
import com.se.webbanhang.repository.StoreRespository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class StoreServiceImpl implements StoreService{
    
    @Autowired
    private UsersService usersService;
    @Autowired
    private StoreRespository storeRespository;
    @Autowired
    private ProductService productService;
    
    
    @Override
    public List<Store> findAll() {
        return storeRespository.findAll();
    }
    
    @Override
    public Store findStoreByUser(int userId) {
        Users theUser = usersService.findbyId(userId);
        Store store = null;
        if(theUser != null)
        {
            store = theUser.getStore();
        }
        return store;
    }

    @Override
    public void save(Store theStore) {
        storeRespository.save(theStore);
    }

    @Override
    public void deleteStore(int id) {
        Store store = findbyId(id);
        if(store != null)
        {
            storeRespository.delete(store);
        }
    }

    @Override
    public Store findbyId(int id) {
        Optional<Store> result = storeRespository.findById(id);
        Store theStore = null;
        if (result.isPresent())
        {
            theStore = result.get();
        }else
            throw new NotFoundException("Not found Store Id: "+id);
        return theStore;
    }

    @Override
    public Store findStoreByProductId(int productId) {
        Products theProducts = productService.findById(productId);
        Users theUsers = theProducts.getUser();
        Store theStore = theUsers.getStore();
        return theStore;
    }

    @Override
    public Integer totalStore() {
       int count = 0;
       List<Store> stores = findAll();
       for(Store s : stores)
       {
           count++;
       }
       return count;
    }
    
}
