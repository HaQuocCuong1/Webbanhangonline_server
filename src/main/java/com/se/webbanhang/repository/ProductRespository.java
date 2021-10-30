/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.repository;

import com.se.webbanhang.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ASUS
 */
@Repository
@Transactional
public interface ProductRespository extends JpaRepository<Products, Integer>{
    @Query("UPDATE Products p SET p.ban_nhanh = ?2 WHERE p.id = ?1")
    @Modifying
    public void updateBannhan(int id, int soluong);
    @Query("UPDATE Products p SET p.status = ?2 WHERE p.id = ?1")
    @Modifying
    public void updateStatusProduct(int id, int type);
    @Query("UPDATE Products p SET p.featured = ?2 WHERE p.id = ?1")
    @Modifying
    public void updateFeaturedProduct(int id, int type);
}
