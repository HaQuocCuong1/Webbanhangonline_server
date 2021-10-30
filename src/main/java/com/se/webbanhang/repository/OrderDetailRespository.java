/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.repository;

import com.se.webbanhang.entity.Order_detail;
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
public interface OrderDetailRespository extends JpaRepository<Order_detail, Integer>{
//    @Query("UPDATE Orders o SET o.status = 1 WHERE o.id = ?1")
//    @Modifying
//    public void updateStatusOrder(int id);
}
