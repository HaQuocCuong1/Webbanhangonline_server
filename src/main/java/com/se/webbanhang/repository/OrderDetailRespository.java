/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.repository;

import com.se.webbanhang.dto.request.RevenueDTO;
import com.se.webbanhang.entity.Order_detail;
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
public interface OrderDetailRespository extends JpaRepository<Order_detail, Integer>{
    @Query("UPDATE Order_detail od SET od.status = ?2 WHERE od.id = ?1")
    @Modifying
    public void updateStatusOrderDetail(int orderDetailId, int type);
    @Query(value = "select count(od.id) from order_detail od join products p "
            + "on od.Products_id =  p.id join users u on p.Users_id = u.id "
            + "where month(dateorder) = ?2 AND u.id=?1", nativeQuery = true
    )
    public Integer totalOrderdetailByMonth(int userId, int month);
    @Query(value = "SELECT SUM(OD.totalmoney) FROM order_detail OD join products p on p.id = OD.Products_id "
            + "join users u on u.id = p.Users_id "
            + "where u.id = ?1 AND month(OD.dateorder) = ?2", nativeQuery = true
    )
    public Double totalrevenueByMonth(int userId, int month);
    @Query(value = "SELECT SUM(OD.totalmoney) FROM order_detail OD join products p on p.id = OD.Products_id "
            + "join users u on u.id = p.Users_id "
            + "where u.id = ?1 AND OD.dateorder between ?2 and ?3", nativeQuery = true
    )
    public Double getTotalrevenueByFromDateAndToDate(int userId, Date fromdate,Date todate);
    @Query(value = "SELECT COUNT(OD.id) FROM order_detail OD join products p on p.id = OD.Products_id "
            + "join users u on u.id = p.Users_id "
            + "where u.id = ?1 AND month(OD.dateorder) = ?2", nativeQuery = true
    )
    public Integer totalorderByMonth(int userId, int month);
    @Query(value = "SELECT COUNT(OD.id) FROM order_detail OD join products p on p.id = OD.Products_id "
            + "join users u on u.id = p.Users_id "
            + "where u.id = ?1 AND OD.dateorder between ?2 and ?3", nativeQuery = true
    )
    public Integer getTotalOrderByFromDateAndToDate(int userId, Date fromdate,Date todate);
//    @Query(value = "SELECT month(OD.dateorder) as thang, SUM(OD.totalmoney) as total "
//            + "FROM order_detail OD join products p on p.id = OD.Products_id "
//            + "join users u on u.id = p.Users_id where u.id = ?1 "
//            + "GROUP BY thang", nativeQuery = true
//    )
//    @Query(value = "SELECT new com.se.webbanhang.dto.request.RevenueDTO(month(OD.dateorder), SUM(OD.totalmoney)) "
//            + "FROM order_detail OD, products p  "
//            + "users u where p.id = OD.Products_id AND u.id = p.Users_id u.id = ?1 "
//            + "GROUP BY month(OD.dateorder)"
//    )
    @Query(name = "getRevenueByUserId_dto",nativeQuery = true)
    public List<RevenueDTO> getRevenueByUserId(int userId);
}
