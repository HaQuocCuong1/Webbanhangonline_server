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
import com.se.webbanhang.entity.Store;
import com.se.webbanhang.entity.Supplier;
import com.se.webbanhang.entity.Users;
import com.se.webbanhang.exception.NotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    @Autowired
    private StoreService storeService;
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

    @Override
    public List<Products> getlistProductsbyStatus(int status) {
        List<Products> listProducts = getlistProducts();
        List<Products> listProduct = new ArrayList<>();
        for(Products p : listProducts)
        {
            if(p.getStatus() == status)
            {
                listProduct.add(p);
            }
        }
        return listProduct;
    }

    @Override
    public Integer getProductInventory(int userId) {
        Users theUsers = usersService.findbyId(userId);
        List<Products> productses = null;
        int totalQuantity = 0;
        if(theUsers != null)
        {
            productses = theUsers.getProducts();
            for(Products p : productses)
            {
                if(p != null)
                {
                    int quantity = p.getQuantity();
                    totalQuantity += quantity;
                }
            }
        }
        return totalQuantity;
    }

    @Override
    public boolean updateQuantityProduct(int updateQuantityProduct, int productId) {
        Products theProduct = findById(productId);
        if(theProduct == null)
           return false;
        else
        {
            productRespository.updateQuantityProduct(theProduct.getId(), updateQuantityProduct);
            return true;
        }
    }

    @Override
    public Integer getTotalProductsSold(int userId) {
        Users theUsers = usersService.findbyId(userId);
        List<Products> productses = null;
        int totalProductSold = 0;
        if(theUsers != null)
        {
            productses = theUsers.getProducts();
            for(Products p : productses)
            {
                if(p != null)
                {
                    int sold = p.getBan_nhanh();
                    totalProductSold += sold;
                }
            }
        }
        return totalProductSold;
    }

    @Override
    public Integer getTotalProductscConfirm(int userId) {
        Users theUsers = usersService.findbyId(userId);
        List<Products> productses = null;
        int totalProductConfirm = 0;
        if(theUsers != null)
        {
            productses = theUsers.getProducts();
            for(Products p : productses)
            {
                if(p.getStatus() == 0)
                {
                    totalProductConfirm++;
                }
            }
        }
        return totalProductConfirm;
    }

    @Override
    public List<Products> getlistProductsbySupplier(int supplierId) {
        Supplier theSupplier = supplierService.findbyId(supplierId);
        List<Products> productses = new ArrayList<>();
        if(theSupplier != null)
        {
            productses = theSupplier.getProducts();
        }
        return productses;
    }

    @Override
    public List<Products> getlistProductsbyPriceIncrease(int categoriesId) {
        Categories theCategories = categoriesService.findbyId(categoriesId);
        List<Products> products = theCategories.getProducts();
        Collections.sort(products, new Comparator<Products>() {
            @Override
            public int compare(Products o1, Products o2) {
                return (int) (o1.getPrice() - o2.getPrice());
            }
        });
        return products;
    }

    @Override
    public List<Products> getlistProductsbyPriceReduced(int categoriesId) {
        Categories theCategories = categoriesService.findbyId(categoriesId);
        List<Products> products = theCategories.getProducts();
        Collections.sort(products, new Comparator<Products>() {
            @Override
            public int compare(Products o1, Products o2) {
                return (int) (o2.getPrice() - o1.getPrice());
            }
        });
        return products;
    }

    @Override
    public List<Products> getlistProductsbySellfast(int categoriesId) {
        Categories theCategories = categoriesService.findbyId(categoriesId);
        List<Products> products = theCategories.getProducts();
        Collections.sort(products, new Comparator<Products>() {
            @Override
            public int compare(Products o1, Products o2) {
                return (int) (o2.getBan_nhanh()- o1.getBan_nhanh());
            }
        });
        return products;
    }

    @Override
    public List<Products> getlistProductsbyPriceIncreaseSupplier(int supplierId) {
        Supplier theSupplier = supplierService.findbyId(supplierId);
        List<Products> products = theSupplier.getProducts();
        Collections.sort(products, new Comparator<Products>() {
            @Override
            public int compare(Products o1, Products o2) {
                return (int) (o1.getPrice() - o2.getPrice());
            }
        });
        return products;
    }

    @Override
    public List<Products> getlistProductsbyPriceReducedSupplier(int supplierId) {
        Supplier theSupplier = supplierService.findbyId(supplierId);
        List<Products> products = theSupplier.getProducts();
        Collections.sort(products, new Comparator<Products>() {
            @Override
            public int compare(Products o1, Products o2) {
                return (int) (o2.getPrice() - o1.getPrice());
            }
        });
        return products;
    }

    @Override
    public List<Products> getlistProductsbySellfastSupplier(int supplierId) {
        Supplier theSupplier = supplierService.findbyId(supplierId);
        List<Products> products = theSupplier.getProducts();
        Collections.sort(products, new Comparator<Products>() {
            @Override
            public int compare(Products o1, Products o2) {
                return (int) (o2.getBan_nhanh()- o1.getBan_nhanh());
            }
        });
        return products;
    }

    @Override
    public List<Products> getlistProductbyPriceIncrease() {
        List<Products> products = getlistProducts();
        Collections.sort(products, new Comparator<Products>() {
            @Override
            public int compare(Products o1, Products o2) {
                return (int) (o1.getPrice() - o2.getPrice());
            }
        });
        return products;
    }

    @Override
    public List<Products> getlistProductbyPriceReduced() {
        List<Products> products = getlistProducts();
        Collections.sort(products, new Comparator<Products>() {
            @Override
            public int compare(Products o1, Products o2) {
                return (int) (o2.getPrice() - o1.getPrice());
            }
        });
        return products;
    }

    @Override
    public List<Products> getlistProductbySellfast() {
        List<Products> products = getlistProducts();
        Collections.sort(products, new Comparator<Products>() {
            @Override
            public int compare(Products o1, Products o2) {
                return (int) (o2.getBan_nhanh()- o1.getBan_nhanh());
            }
        });
        return products;
    }

    @Override
    public List<Products> getListproductBystoreId(int storeId) {
        Store store = storeService.findbyId(storeId);
        Users theUsers = store.getUsers();
        List<Products> productses = new ArrayList<>();
        if(theUsers != null)
        {
            productses = theUsers.getProducts();
        }
        
        return productses;
    }

    @Override
    public List<Products> getlistProductsbyPriceIncreaseStore(int storeId) {
        Store store = storeService.findbyId(storeId);
        Users theUsers = store.getUsers();
        List<Products> products = theUsers.getProducts();
        Collections.sort(products, new Comparator<Products>() {
            @Override
            public int compare(Products o1, Products o2) {
                return (int) (o1.getPrice() - o2.getPrice());
            }
        });
        return products;
    }

    @Override
    public List<Products> getlistProductsbyPriceReducedStore(int storeId) {
        Store store = storeService.findbyId(storeId);
        Users theUsers = store.getUsers();
        List<Products> products = theUsers.getProducts();
        Collections.sort(products, new Comparator<Products>() {
            @Override
            public int compare(Products o1, Products o2) {
                return (int) (o2.getPrice() - o1.getPrice());
            }
        });
        return products;
    }

    @Override
    public List<Products> getlistProductsbySellfastStore(int storeId) {
        Store store = storeService.findbyId(storeId);
        Users theUsers = store.getUsers();
        List<Products> products = theUsers.getProducts();
        Collections.sort(products, new Comparator<Products>() {
            @Override
            public int compare(Products o1, Products o2) {
                return (int) (o2.getBan_nhanh()- o1.getBan_nhanh());
            }
        });
        return products;
    }

    @Override
    public List<Products> getlistProductsbySearchText(String searchText) {
        List<Products> productses = null;
        if(searchText != null)
            productses = productRespository.findAllProduct(searchText);
        else
            productses = productRespository.findAll();
        return productses;
    }

    @Override
    public List<Products> listproductForCategoriesByStore(int categoriesId, int storeId) {
        List<Categories> listCategories = categoriesService.getCategoriBystoreId(storeId);
        Users theUsers = usersService.findByStoreId(storeId);
        List<Products> productses1 = new ArrayList<>();
        for(Categories theCategories : listCategories)
        {
            if(theCategories.getId() == categoriesId)
            {
                List<Products> productses = theCategories.getProducts();
                for(Products p : productses)
                {
                    if(p.getUser().getId() == theUsers.getId())
                        productses1.add(p);
                }
            }
        }
        return productses1;
    }

    @Override
    public List<Products> getproductForIncreaseByCategoriesIdAndStoreId(int categoriesId, int storeId) {
        List<Products> productses = listproductForCategoriesByStore(categoriesId, storeId);
        Collections.sort(productses, new Comparator<Products>() {
            @Override
            public int compare(Products o1, Products o2) {
                return (int) (o1.getPrice() - o2.getPrice());
            }
        });
        return productses;
    }

    @Override
    public List<Products> getproductForReducedByCategoriesIdAndStoreId(int categoriesId, int storeId) {
        List<Products> productses = listproductForCategoriesByStore(categoriesId, storeId);
        Collections.sort(productses, new Comparator<Products>() {
            @Override
            public int compare(Products o1, Products o2) {
                return (int) (o2.getPrice() - o1.getPrice());
            }
        });
        return productses;
    }

    @Override
    public List<Products> getproductForSellfastByCategoriesIdAndStoreId(int categoriesId, int storeId) {
        List<Products> productses = listproductForCategoriesByStore(categoriesId, storeId);
        Collections.sort(productses, new Comparator<Products>() {
            @Override
            public int compare(Products o1, Products o2) {
                return (int) (o2.getBan_nhanh()- o1.getBan_nhanh());
            }
        });
        return productses;
    }

    @Override
    public List<Products> getproductForDiscountByCategoriesIdAndStoreId(int categoriesId, int storeId) {
        List<Products> productses = listproductForCategoriesByStore(categoriesId, storeId);
        Collections.sort(productses, new Comparator<Products>() {
            @Override
            public int compare(Products o1, Products o2) {
                return (int) (o2.getPromotion()- o1.getPromotion());
            }
        });
        return productses;
    }

    @Override
    public Integer totalAllProducByUser(int userId) {
        int count = 0;
        Users theUsers = usersService.findbyId(userId);
        if(theUsers != null)
        {
            List<Products> products = theUsers.getProducts();
            for(Products p : products)
            {
                count++;
            }
        }else
            throw new NotFoundException("Not found User id: "+ userId);
        return count;
    }
    
}
