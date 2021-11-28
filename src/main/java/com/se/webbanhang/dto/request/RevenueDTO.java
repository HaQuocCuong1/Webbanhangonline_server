/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se.webbanhang.dto.request;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedNativeQuery(name = "getRevenueByUserId_dto",
        query = "SELECT OD.id as id,month(OD.dateorder) as thang, SUM(OD.totalmoney) as total "
            + "FROM order_detail OD join products p on p.id = OD.Products_id "
            + "join users u on u.id = p.Users_id where u.id = ?1 "
            + "GROUP BY thang "
            + "ORDER BY thang ASC", resultSetMapping = "mappingrevenuedto"
        )
@SqlResultSetMapping(name = "mappingrevenuedto",
        classes = @ConstructorResult(targetClass = RevenueDTO.class, 
                columns = {
                    @ColumnResult(name = "id", type = Integer.class),
                    @ColumnResult(name = "thang", type = Integer.class),
                    @ColumnResult(name = "total", type = Double.class)
                }))
public class RevenueDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    
    private int thang;
  
    private double total;

    public RevenueDTO(int thang, double total) {
        this.thang = thang;
        this.total = total;
    }
    
}
