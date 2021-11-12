/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.service;

import com.se.webbanhang.repository.CategoriesRespository;
import com.se.webbanhang.entity.Categories;
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
public class CategoriesServicecImpl implements CategoriesService{
    
    private CategoriesRespository categoriesRespository;
    @Autowired
    public CategoriesServicecImpl(CategoriesRespository categoriesRespository) {
        this.categoriesRespository = categoriesRespository;
    }
    
    @Override
    public List<Categories> findAll() {
        return categoriesRespository.findAll();
    }

    @Override
    public Categories findbyId(int id) {
        Optional<Categories> result = categoriesRespository.findById(id);
        Categories theCategories = null;
        if (result.isPresent())
        {
            theCategories = result.get();
        }else {
            throw new NotFoundException("Did not find Categories id: "+id);
        }
        return theCategories;
    }

    @Override
    public void save(Categories theCategories) {
        categoriesRespository.save(theCategories);
    }

    @Override
    public void delete(int id) {
      categoriesRespository.deleteById(id);
    }
}
