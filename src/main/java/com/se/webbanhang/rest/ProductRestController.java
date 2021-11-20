/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.rest;

import com.se.webbanhang.dto.request.PictureProductDTO;
import com.se.webbanhang.dto.request.ProductDTO;
import com.se.webbanhang.dto.request.SearchProductDTO;
import com.se.webbanhang.dto.request.UpdateProductDTO;
import com.se.webbanhang.repository.ProductRespository;
import com.se.webbanhang.entity.Categories;
import com.se.webbanhang.entity.Picture_product;
import com.se.webbanhang.entity.Products;
import com.se.webbanhang.entity.Supplier;
import com.se.webbanhang.entity.Users;
import com.se.webbanhang.model.Product;
import com.se.webbanhang.service.CategoriesService;
import com.se.webbanhang.service.PictureProductService;
import com.se.webbanhang.service.ProductService;
import com.se.webbanhang.service.SupplierService;
import com.se.webbanhang.service.UsersService;
import java.sql.Date;
import java.util.List;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/api")
public class ProductRestController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private PictureProductService pictureProductService;
//    @Autowired
//    private OrderdetaiService orderdetaiService;
    @PostMapping("/products")
    public Products saveProduct(@RequestBody ProductDTO theProducts) throws NotFoundException {
        theProducts.setId(0);
        Users theUsers = usersService.findbyId(theProducts.getUserId());
        Products product = null;
        if(theProducts != null)
        {
            Categories theCategories = categoriesService.findbyId(theProducts.getCategoriesId());
            if (theCategories != null)
            {
                Supplier theSupplier = supplierService.findbyId(theProducts.getSuppliersId());
                if (theSupplier != null)
                {
                    product = new Products();
                    product.setPromotion(0);
                    product.setCompetitive_price(theProducts.getPrice());
                    product.setName(theProducts.getName());
                    product.setCode(theProducts.getCode());
                    product.setSort_description(theProducts.getSort_description());
                    product.setDetail_description(theProducts.getDetail_description());
                    product.setPrice(theProducts.getPrice());
                    product.setQuantity(theProducts.getQuantity());
                    product.setCategories(theCategories);
                    product.setSupplier(theSupplier);
                    product.setUser(theUsers);
                    
                    long millis=System.currentTimeMillis();
                    product.setDate_sale(new Date(millis));
                    
                    theUsers.addProduct(product);
                    theSupplier.addProduct(product);
                    theCategories.addProduct(product);
                    theCategories.setTotalproduct();
                    List<PictureProductDTO> listPicture = theProducts.getListpictureProductDTOs();
                    for(PictureProductDTO picture : listPicture)
                    {
                        if(picture != null)
                        {
                            if(picture.equals(listPicture.get(0)))
                            {
                                product.setAvartar(picture.getFile());
                                productService.saveProducts(product, theProducts.getCategoriesId(), theProducts.getSuppliersId(), theProducts.getUserId());
                            }else{
                                Picture_product pic = new Picture_product();
                                pic.setId(0);
                                pic.setFile(picture.getFile());
                                Products p = productService.findById(product.getId());
                                pic.setProducts(p);
                                pictureProductService.save(pic);
                            }
                        }
                    }
      
                }
            }
        }else {
            throw new NotFoundException("product not empty");
        }
        return product;
    }
    @PutMapping("/products")
    public Products updateProduct(@RequestBody UpdateProductDTO updatePtodDTO) throws NotFoundException
    {
        
        Users theUsers = usersService.findbyId(updatePtodDTO.getUserId());
        Products theProducts1 = null;
        if(updatePtodDTO != null)
        {
            if (theUsers != null)
            {
                Categories theCategories = categoriesService.findbyId(updatePtodDTO.getCategoriesId());
                if (theCategories != null)
                {
                    Supplier theSupplier = supplierService.findbyId(updatePtodDTO.getSuppliersId());
                    if (theSupplier != null)
                    {
                        Products products2 = productService.findById(updatePtodDTO.getId());
                        theProducts1 = new Products();
                        double competivePrice = 0;
                        double percen = (100-updatePtodDTO.getPromotion());
                        double promotionProduct = percen/100;
                        double priceProduct = updatePtodDTO.getPrice();
                        competivePrice = priceProduct*promotionProduct;


                        theProducts1.setId(updatePtodDTO.getId());
                        theProducts1.setCompetitive_price(competivePrice);
                        theProducts1.setName(updatePtodDTO.getName());
                        theProducts1.setCode(updatePtodDTO.getCode());
                        theProducts1.setSort_description(updatePtodDTO.getSort_description());
                        theProducts1.setDetail_description(updatePtodDTO.getDetail_description());
                        theProducts1.setPrice(updatePtodDTO.getPrice());
                        theProducts1.setAvartar(updatePtodDTO.getAvartar());
                        theProducts1.setDate_sale(updatePtodDTO.getDate_sale());
                        theProducts1.setQuantity(updatePtodDTO.getQuantity());
                        theProducts1.setPromotion(updatePtodDTO.getPromotion());
                        theProducts1.setStatus(products2.getStatus());
                        theProducts1.setFeatured(products2.getFeatured());
                        theProducts1.setBan_nhanh(products2.getBan_nhanh());

                        theProducts1.setCategories(theCategories);
                        theProducts1.setSupplier(theSupplier);
                        theProducts1.setUser(theUsers);
                        productService.updateProducts(theProducts1);
                        List<PictureProductDTO> listPicture = updatePtodDTO.getListpictureProductDTOs();
                        for(PictureProductDTO picture : listPicture)
                        {
                            if(picture != null)
                            {
                                Picture_product pic = new Picture_product();
                                pic.setId(picture.getId());
                                pic.setFile(picture.getFile());
                                Products p = productService.findById(theProducts1.getId());
                                pic.setProducts(p);
                                pictureProductService.save(pic);
                                
                            }
                        }
                    }
                }
            }
        }else {
            throw new NotFoundException("product not empty");
        }
        return theProducts1;
    } 
    @DeleteMapping("/products/{productId}")
    public String deleteProduct(@PathVariable int productId)
    {
       Products theProducts = productService.findById(productId);
       if (theProducts == null)
        {
            throw new RuntimeException("Product id not found - " + productId);
        }
        else {
            productService.deleteProducts(productId);
        }

        return "Deleted product id - " + productId;
    }
    @GetMapping("/products")
    public List<Products> listproduct() {
        // get persons from the service
        List<Products> listproduct = productService.getlistProducts();
        return listproduct; 
    }
    @GetMapping("/products/categories/{categorieId}")
    public List<Products> listproductForCategori(@PathVariable int categorieId) {
        // get persons from the service
        List<Products> listproduct = productService.getlistProductsbyCategori(categorieId);
        return listproduct; 
    }
    @GetMapping("/products/supplier/{supplierId}")
    public List<Products> listproductForSupplier(@PathVariable int supplierId) {
        // get persons from the service
        List<Products> listproduct = productService.getlistProductsbySupplier(supplierId);
        return listproduct; 
    }
    
    @GetMapping("/products/{productId}")
    public Products getproduct(@PathVariable int productId) throws NotFoundException {
        // get persons from the service
        Products theproduct = productService.getProduct(productId);
        if (theproduct == null)
            {
                throw new NotFoundException("products not found - "+ productId);
            }
        return theproduct; 
    }
    @GetMapping("/products/{productId}/categories/{categoriesId}")
    public Products getproductForcategori(@PathVariable int productId, @PathVariable int categoriesId) throws NotFoundException {
        // get persons from the service
        Categories categories = categoriesService.findbyId(categoriesId);
        Products theproduct = categories.getProduct(productId);
        if (theproduct == null)
        {
            throw new NotFoundException("products not found - "+ productId);
        }
        return theproduct; 
    }
    @GetMapping("/products/users/{userId}")
    public List<Products> listproductForUser(@PathVariable int userId) {
        // get persons from the service
        List<Products> listproduct = productService.getlistProductsbyUser(userId);
        return listproduct; 
    }
    @GetMapping("/products/status/{status}")
    public List<Products> listproductForStatus(@PathVariable int status) {
        List<Products> listproduct = productService.getlistProductsbyStatus(status);
        return listproduct; 
    }
    @GetMapping("/products/inventory/users/{userId}")
    public Integer countProduct(@PathVariable int userId) {
        // get persons from the service
        return productService.getProductInventory(userId); 
    }
    @GetMapping("/products/totalproduct/users/{userId}")
    public Integer totalProductsSold(@PathVariable int userId) {
        // get persons from the service
        return productService.getTotalProductsSold(userId);
    }
    @GetMapping("/products/totalconfirm/users/{userId}")
    public Integer totalProductsConfirm(@PathVariable int userId) {
        // get persons from the service
        return productService.getTotalProductscConfirm(userId);
    }
    @GetMapping("/products/increase")
    public List<Products> listproductPriceIncrease() {
        List<Products> listproduct = productService.getlistProductbyPriceIncrease();
        return listproduct; 
    }
    @GetMapping("/products/increase/categories/{categoriesId}")
    public List<Products> listproductForPriceIncrease(@PathVariable int categoriesId) {
        List<Products> listproduct = productService.getlistProductsbyPriceIncrease(categoriesId);
        return listproduct; 
    }
    @GetMapping("/products/increase/supplier/{supplierId}")
    public List<Products> listproductForPriceIncreaseSupplier(@PathVariable int supplierId) {
        List<Products> listproduct = productService.getlistProductsbyPriceIncreaseSupplier(supplierId);
        return listproduct; 
    }
    @GetMapping("/products/reduced")
    public List<Products> listproductByPriceReduced() {
        List<Products> listproduct = productService.getlistProductbyPriceReduced();
        return listproduct; 
    }
    @GetMapping("/products/reduced/categories/{categoriesId}")
    public List<Products> listproductForPriceReduced(@PathVariable int categoriesId) {
        List<Products> listproduct = productService.getlistProductsbyPriceReduced(categoriesId);
        return listproduct; 
    }
    @GetMapping("/products/reduced/supplier/{supplierId}")
    public List<Products> listproductForPriceReducedSupplier(@PathVariable int supplierId) {
        List<Products> listproduct = productService.getlistProductsbyPriceReducedSupplier(supplierId);
        return listproduct; 
    }
    @GetMapping("/products/sellfast")
    public List<Products> listproductBySellfast() {
        List<Products> listproduct = productService.getlistProductbySellfast();
        return listproduct; 
    }
    @GetMapping("/products/sellfast/categories/{categoriesId}")
    public List<Products> listproductForSellfast(@PathVariable int categoriesId) {
        List<Products> listproduct = productService.getlistProductsbySellfast(categoriesId);
        return listproduct; 
    }
    @GetMapping("/products/sellfast/supplier/{supplierId}")
    public List<Products> listproductForSellfastSupplier(@PathVariable int supplierId) {
        List<Products> listproduct = productService.getlistProductsbySellfastSupplier(supplierId);
        return listproduct; 
    }
    @GetMapping("/products/store/{storeId}")
    public List<Products> listproductBystoreId(@PathVariable int storeId) {
        List<Products> listproduct = productService.getListproductBystoreId(storeId);
        return listproduct; 
    }
    @GetMapping("/products/increase/store/{storeId}")
    public List<Products> listproductForPriceIncreaseByStore(@PathVariable int storeId) {
        List<Products> listproduct = productService.getlistProductsbyPriceIncreaseStore(storeId);
        return listproduct; 
    }
    @GetMapping("/products/reduced/store/{storeId}")
    public List<Products> listproductForPriceReducedByStore(@PathVariable int storeId) {
        List<Products> listproduct = productService.getlistProductsbyPriceReducedStore(storeId);
        return listproduct; 
    }
    @GetMapping("/products/sellfast/store/{storeId}")
    public List<Products> listproductForSellfastStore(@PathVariable int storeId) {
        List<Products> listproduct = productService.getlistProductsbySellfastStore(storeId);
        return listproduct; 
    }
    @PostMapping("/products/searchtext")
    public List<Products> searcehText(@RequestBody SearchProductDTO searchtext)
    {
        return productService.getlistProductsbySearchText(searchtext.getSearchtext());
    }
    @PostMapping("/status/products/{productId}/type/{type}")
    public String updateStatusProduct(@PathVariable int productId, @PathVariable int type)
    {
        boolean checkupdate = productService.updateStatusProduct(productId, type);
        if(checkupdate == true)
            return "Update status products susscess!";
        else
            return "Update status products fail!";
    }
    @PostMapping("/featured/products/{productId}/type/{type}")
    public String updateFeatured(@PathVariable int productId, @PathVariable int type)
    {
        boolean checkupdate = productService.updateFeaturedProduct(productId, type);
        if(checkupdate == true)
            return "Update featured products susscess!";
        else
            return "Update featured products fail!";
    }
    
}
