/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.service;

import com.se.webbanhang.repository.CategoriesRespository;
import com.se.webbanhang.repository.ProductRespository;
import com.se.webbanhang.entity.Categories;
import com.se.webbanhang.entity.Order_detail;
import com.se.webbanhang.entity.Products;
import com.se.webbanhang.entity.Supplier;
import com.se.webbanhang.entity.Users;
import com.se.webbanhang.exception.NotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRespository productRespository;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private UsersService usersService;
    @Override
    public Products getProduct(int productsId) {
        Optional<Products> result = productRespository.findById(productsId);
        Products theProducts = null;
        if (result.isPresent())
        {
            theProducts = result.get();
        }else {
            throw new NotFoundException("Did not find product id: "+productsId);
        }
        return theProducts;
        
    }

    @Override
    public List<Products> getlistProducts() {
        return productRespository.findAll();
    }

    @Override
    public List<Products> getlistProductsbyCategori(int categories) {
        Categories theCategories = categoriesService.findbyId(categories);
        List<Products> listProduct = null;
        if (theCategories != null)
        {
            listProduct = theCategories.getProducts();
        }
        return listProduct;
    }

    @Override
    public void saveProducts(Products theproduct, int categorieId, int supplierId, int userId) {
       Categories theCategories = categoriesService.findbyId(categorieId);
       Supplier theSupplier = supplierService.findbyId(supplierId);
       Users theUsers = usersService.findbyId(userId);
       theSupplier.addProduct(theproduct);
       theCategories.addProduct(theproduct);
       theUsers.addProduct(theproduct);
       productRespository.save(theproduct);
    }

    @Override
    public void updateProducts(Products products) {
       // Products theProducts = getProduct(productId);
       // Products theProducts = categories.getProduct(tempproduct.getId());
      //  setValueProducts(theProducts, tempproduct);
         productRespository.save(products);
    }

    @Override
    public void deleteProducts(int productId) {
       productRespository.deleteById(productId);
    }
    private void setValueProducts(Products theProducts, Products tempProducts)
    {
        theProducts.setName(tempProducts.getName());
        theProducts.setCode(tempProducts.getCode());
        theProducts.setSort_description(tempProducts.getSort_description());
        theProducts.setDetail_description(tempProducts.getDetail_description());
        theProducts.setBan_nhanh(tempProducts.getBan_nhanh());
        theProducts.setPrice(tempProducts.getPrice());
        theProducts.setFeatured(tempProducts.getFeatured());
        theProducts.setCompetitive_price(tempProducts.getCompetitive_price());
        theProducts.setAvartar(tempProducts.getAvartar());
        theProducts.setDate_sale(tempProducts.getDate_sale());
        theProducts.setQuantity(tempProducts.getQuantity());
       
    }

    @Override
    public Products findById(int productId) {
        Optional<Products> result = productRespository.findById(productId);
        Products theProducts = null;
        if (result.isPresent())
        {
            theProducts = result.get();
        }else {
            throw new NotFoundException("Did not find product id: "+productId);
        }
        return theProducts;
    }

    @Override
    public void save(Products theProducts) {
       productRespository.save(theProducts);
    }
    
    public void updateBannhan(int id, int soluong)
    {
        productRespository.updateBannhan(id, soluong);
    }

    @Override
    public List<Products> getlistProductsbyUser(int userId) {
        Users theUsers = usersService.findbyId(userId);
        List<Products> listProduct = null;
        if (theUsers != null)
        {
            listProduct = theUsers.getProducts();
        }
        return listProduct;
    }

    @Override
    public boolean updateStatusProduct(int id, int type) {
       Products theProducts = findById(id);
       if(theProducts == null)
           return false;
       else
       {
           productRespository.updateStatusProduct(theProducts.getId(), type);
           return true;
       }
    }

    @Override
    public boolean updateFeaturedProduct(int id, int type) {
       Products theProduct = findById(id);
       if(theProduct == null)
           return false;
       else
       {
           productRespository.updateFeaturedProduct(theProduct.getId(), type);
           return true;
       }
    }
}
