/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.rest;

import com.se.webbanhang.entity.Categories;
import com.se.webbanhang.entity.Supplier;
import com.se.webbanhang.exception.NotFoundException;
import com.se.webbanhang.service.CategoriesService;
import com.se.webbanhang.service.SupplierService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
public class SupplierRestController {
   
    private SupplierService supplierService;
    @Autowired
    public SupplierRestController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }
    
    
    @GetMapping("/suppliers")
    public List<Supplier> findAll()
    {
        return supplierService.findAll();
    }
    @GetMapping("/suppliers/{supplierId}")
    public Supplier findByid(@PathVariable int supplierId)
    {
        Supplier theSupplier = supplierService.findbyId(supplierId);
        if (theSupplier == null)
        {
            throw new NotFoundException("Supplier not find by "+ supplierId);
        }
        return theSupplier;
    }
    @PostMapping("/suppliers")
    public Supplier save(@RequestBody Supplier theSupplier)
    {
        theSupplier.setId(0);
        supplierService.save(theSupplier);
        return theSupplier;
    }
    @PutMapping("/suppliers")
    public Supplier update(@RequestBody Supplier theSupplier)
    {
        supplierService.save(theSupplier);
        return theSupplier;
    }
    @DeleteMapping("/suppliers/{supplierId}")
    public String delete (@PathVariable int supplierId)
    {
        Supplier tempSupplier = supplierService.findbyId(supplierId);
        if (tempSupplier == null)
        {
            throw new NotFoundException("Supplier id not found - " + supplierId);
        } else {
            supplierService.delete(supplierId);
        }
        return "Delete supplier Id: "+supplierId;
    }
}
