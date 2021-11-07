/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.se.webbanhang.service;

import com.se.webbanhang.entity.Store;

/**
 *
 * @author Admin
 */
public interface StoreService {
    public Store findStoreByUser(int userId);
    public Store findbyId(int id);
    public void save(Store theStore);
    public void deleteStore(int id);
}
