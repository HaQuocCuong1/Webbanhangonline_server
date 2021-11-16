/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.service;

import com.se.webbanhang.entity.Products;
import com.se.webbanhang.entity.Users;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface ProductService {
    public void save(Products theProducts);
    public Products getProduct(int productsId);
    public Products findById(int productId);
    public List<Products> getlistProducts();
    public List<Products> getlistProductsbyCategori(int categories);
    public void saveProducts(Products theproduct, int categorieId, int supplierId, int userId);
    public void updateProducts(Products theproduct);
    public void deleteProducts(int productId);
    public void updateBannhan(int id, int soluong);
    public List<Products> getlistProductsbyUser(int userId);
    public boolean updateStatusProduct(int id, int type);
    public boolean updateFeaturedProduct(int id, int type);
    public List<Products> getlistProductsbyStatus(int status);
    public Integer getProductInventory();
}
