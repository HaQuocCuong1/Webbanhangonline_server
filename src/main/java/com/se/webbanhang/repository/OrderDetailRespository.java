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
    @Query("UPDATE Order_detail od SET od.status = ?2 WHERE od.id = ?1")
    @Modifying
    public void updateStatusOrderDetail(int orderDetailId, int type);
    @Query(value = "select count(od.id) from order_detail od join products p "
            + "on od.Products_id =  p.id join users u on p.Users_id = u.id "
            + "where month(dateorder) = ?2 AND u.id=?1", nativeQuery = true
    )
    public Integer totalOrderdetailByMonth(int userId, int month);
}
