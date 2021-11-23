/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.service;

import com.se.webbanhang.entity.Categories;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface CategoriesService {
    public List<Categories> findAll();
    public Categories findbyId(int id);
    public void save(Categories theCategories);
    public void delete(int id);
    public List<Categories> getCategoriBystoreId(int storeId);
}
