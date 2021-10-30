/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.service;

import com.se.webbanhang.entity.Picture_product;
import com.se.webbanhang.repository.PictureProductRespository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class PictureProductServiceImpl implements PictureProductService{
    @Autowired
    private PictureProductRespository pictureProductRespository;
    @Override
    public List<Picture_product> findAll() {
       return pictureProductRespository.findAll();
    }

    @Override
    public Picture_product findbyId(int id) {
        Optional<Picture_product> result = pictureProductRespository.findById(id);
        Picture_product thePicture_product = null;
        if (result.isPresent())
        {
            thePicture_product = result.get();
        }else {
            throw new RuntimeException("Did not find PictureProduct id: "+id);
        }
        return thePicture_product;
    }

    @Override
    public Optional<Picture_product> findByFile(String filename) {
        return pictureProductRespository.findByFile(filename);
    }

    @Override
    public void save(Picture_product thePictureproduct) {
        pictureProductRespository.save(thePictureproduct);
    }

    @Override
    public void delete(int id) {
       pictureProductRespository.deleteById(id);
    }
    
}
