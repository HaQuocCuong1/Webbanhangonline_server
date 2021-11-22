/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.repository;

import com.se.webbanhang.entity.Products;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Query("UPDATE Products p SET p.quantity = ?2 WHERE p.id = ?1")
    @Modifying
    public void updateQuantityProduct(int id, int value);
    @Query("SELECT p FROM Products p WHERE "
            + "CONCAT(p.id, p.name, p.sort_description, p.detail_description, p.price)"
            + " LIKE %?1%")
    @Modifying
    public List<Products> findAllProduct(String searchText);
//    @Query(value = "SELECT p FROM Products p WHERE p.date between ?1 and ?2", nativeQuery = true)
//    public List<Products> findByStartDateBetween(Date fromDate, Date toDate);
}
