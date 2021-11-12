/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.service;

import com.se.webbanhang.repository.SupplierRespository;
import com.se.webbanhang.entity.Categories;
import com.se.webbanhang.entity.Supplier;
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
public class SupplierServiceImpl implements SupplierService{
    @Autowired
    private SupplierRespository supplierRespository;

    @Override
    public List<Supplier> findAll() {
        return supplierRespository.findAll();
    }

    @Override
    public Supplier findbyId(int id) {
        Optional<Supplier> result = supplierRespository.findById(id);
        Supplier theSupplier = null;
        if (result.isPresent())
        {
            theSupplier = result.get();
        }else {
            throw new NotFoundException("Did not find Supplier id: "+id);
        }
        return theSupplier;
    }

    @Override
    public void save(Supplier theSupplier) {
        supplierRespository.save(theSupplier);
    }

    @Override
    public void delete(int id) {
        supplierRespository.deleteById(id);
    }

    
    
}
