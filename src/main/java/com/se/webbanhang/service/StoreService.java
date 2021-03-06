/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.se.webbanhang.service;

import com.se.webbanhang.dto.request.ListStore;
import com.se.webbanhang.dto.request.QueryStoreDTO;
import com.se.webbanhang.entity.Store;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface StoreService {
    public List<Store> findAll();
    public Store findStoreByUser(int userId);
    public Store findbyId(int id);
    public void save(Store theStore);
    public void deleteStore(int id);
    public Store findStoreByProductId(int productId);
    public Integer totalStore();
    public ListStore findAllSore();
}
