/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.repository;

import com.se.webbanhang.entity.Categories;
import com.se.webbanhang.entity.Picture_product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ASUS
 */
@Repository
public interface PictureProductRespository extends JpaRepository<Picture_product, Integer>{
    Optional<Picture_product> findByFile(String filename);
}
