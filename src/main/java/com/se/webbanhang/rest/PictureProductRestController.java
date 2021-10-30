/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.rest;

import com.se.webbanhang.entity.Picture_product;
import com.se.webbanhang.entity.Products;
import com.se.webbanhang.service.PictureProductService;
import com.se.webbanhang.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
public class PictureProductRestController {
    @Autowired
    private ProductService productService;
    @Autowired
    private PictureProductService pictureProductService;
    @GetMapping("/pictureProduct")
    public List<Picture_product> findAll()
    {
        return pictureProductService.findAll();
    }
    @PostMapping("/pictureProduct/products/{productId}")
    public Picture_product savePictureProductByProduct(@RequestBody Picture_product thePicture_product, @PathVariable int productId)
    {
        Products theProducts = productService.findById(productId);
        thePicture_product.setId(0);
        thePicture_product.setProducts(theProducts);
        theProducts.addPrProduct(thePicture_product);
        pictureProductService.save(thePicture_product);
        return thePicture_product;
    }
    @PutMapping("/pictureProduct/products/{productId}")
    public Picture_product update(@RequestBody Picture_product thePicture_product, @PathVariable int productId)
    {
        Products theProducts = productService.findById(productId);
        thePicture_product.setProducts(theProducts);
        pictureProductService.save(thePicture_product);
        return thePicture_product;
    }
    @DeleteMapping("/pictureProduct/{prProductId}")
    public String deletePictureProduct (@PathVariable int prProductId)
    {
        Picture_product tempPictureproduct = pictureProductService.findbyId(prProductId);
        if (tempPictureproduct == null)
        {
            throw new RuntimeException("Pictoru Product id not found - " + prProductId);
        } else {
           //roleUserService.delete(roleId);
           pictureProductService.delete(prProductId);
        }
        return "Delete Picture product Id: "+prProductId;
    }
}
