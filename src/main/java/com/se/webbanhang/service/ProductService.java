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
    public Integer getProductInventory(int userId);
    public boolean updateQuantityProduct(int updateQuantityProduct, int productId);
    public Integer getTotalProductsSold(int userId);
    public Integer getTotalProductscConfirm(int userId);
    public List<Products> getlistProductsbySupplier(int supplierId);
    public List<Products> getlistProductsbyPriceIncrease(int categoriesId);
    public List<Products> getlistProductsbyPriceReduced(int categoriesId);
    public List<Products> getlistProductsbySellfast(int categoriesId);
    public List<Products> getlistProductsbyPriceIncreaseSupplier(int supplierId);
    public List<Products> getlistProductsbyPriceReducedSupplier(int supplierId);
    public List<Products> getlistProductsbySellfastSupplier(int supplierId);
    public List<Products> getlistProductbyPriceIncrease();
    public List<Products> getlistProductbyPriceReduced();
    public List<Products> getlistProductbySellfast();
    public List<Products> getListproductBystoreId(int storeId);
    public List<Products> getlistProductsbyPriceIncreaseStore(int storeId);
    public List<Products> getlistProductsbyPriceReducedStore(int storeId);
    public List<Products> getlistProductsbySellfastStore(int storeId);
    public List<Products> getlistProductsbySearchText(String searchText);
    public List<Products> listproductForCategoriesByStore(int categoriesId,int storeId);
    public List<Products> getproductForIncreaseByCategoriesIdAndStoreId(int categoriesId,int storeId);
    public List<Products> getproductForReducedByCategoriesIdAndStoreId(int categoriesId,int storeId);
    public List<Products> getproductForSellfastByCategoriesIdAndStoreId(int categoriesId,int storeId);
    public List<Products> getproductForDiscountByCategoriesIdAndStoreId(int categoriesId,int storeId);
    public List<Products> listproductnews();
    public List<Products> listproductnewsByCategorieId(int categorieId);
    public List<Products> listproductnewsBySupplierId(int supplierId);
    public List<Products> listproductnewsByStoreId(int storeId);
    public Integer totalAllProducByUser(int userId);
    public Integer totalApproveProduct();
    public List<Products> listProductInventory(int userId);
    public List<Products> listProductSold(int userId);
}
