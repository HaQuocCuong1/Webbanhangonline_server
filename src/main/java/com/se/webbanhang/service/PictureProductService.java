/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.service;

import com.se.webbanhang.entity.Picture_product;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ASUS
 */
public interface PictureProductService {
    public List<Picture_product> findAll();
    public Picture_product findbyId(int id);
    Optional<Picture_product> findByFile(String filename);
    public void save(Picture_product thePictureproduct);
    public void delete(int id);
}
