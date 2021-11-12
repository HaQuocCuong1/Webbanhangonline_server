/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.rest;

import com.se.webbanhang.entity.Categories;
import com.se.webbanhang.entity.Products;
import com.se.webbanhang.exception.NotFoundException;
import com.se.webbanhang.service.CategoriesService;
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
public class CategoriesRestController {
   
    private CategoriesService categoriesService;
    @Autowired
    public CategoriesRestController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }
    
    
    @GetMapping("/categories")
    public List<Categories> findAll()
    {
        return categoriesService.findAll();
    }
    @GetMapping("/categories/{categoriesId}")
    public Categories findByid(@PathVariable int categoriesId)
    {
        Categories thecate = categoriesService.findbyId(categoriesId);
        if (thecate == null)
        {
            throw new NotFoundException("Categories not find by "+ categoriesId);
        }
        return thecate;
    }
    @GetMapping("/categories/{categoriesId}/products")
    public List<Products> findProductBycateid(@PathVariable int categoriesId)
    {
        Categories thecate = categoriesService.findbyId(categoriesId);
        List<Products> products = null;
        if (thecate == null)
        {
            throw new NotFoundException("Categories not find by "+ categoriesId);
        }else {
            products = thecate.getProducts();
        }
        return products;
    }
    @PostMapping("/categories")
    public Categories save(@RequestBody Categories theCategories)
    {
        //Categories theCategories1 = new Categories();
        theCategories.setId(0);
     //   theCategories.setTotalproduct(theCategories.getTotalListProduct());
        categoriesService.save(theCategories);
        return theCategories;
    }
    @PutMapping("/categories")
    public Categories update(@RequestBody Categories theCategories)
    {
        categoriesService.save(theCategories);
        return theCategories;
    }
    @DeleteMapping("/categories/{categoriesId}")
    public String delete (@PathVariable int categoriesId)
    {
        Categories tempCategories = categoriesService.findbyId(categoriesId);
        if (tempCategories == null)
        {
            throw new NotFoundException("Categories id not found - " + categoriesId);
        } else {
            categoriesService.delete(categoriesId);
        }
        return "Delete categories Id: "+categoriesId;
    }
}
