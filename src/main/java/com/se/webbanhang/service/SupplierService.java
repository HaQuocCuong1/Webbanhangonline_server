/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.service;

import com.se.webbanhang.entity.Categories;
import com.se.webbanhang.entity.Supplier;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface SupplierService {
    public List<Supplier> findAll();
    public Supplier findbyId(int id);
    public void save(Supplier theSupplier);
    public void delete(int id);
}
