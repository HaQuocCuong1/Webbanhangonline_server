/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se.webbanhang.dto.request;

import java.util.Date;
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
@NamedNativeQuery(name = "getQueryStoreDTO",
        query = "SELECT s.id as id,s.logo as logo, s.name as name, u.name as representative, u.address as address, u.phone as phone, s.datestore as sortdate "
            + "FROM store s join users u on s.Users_id = u.id "
            + "ORDER BY sortdate ASC", resultSetMapping = "mappingstoredto"
        )
@SqlResultSetMapping(name = "mappingstoredto",
        classes = @ConstructorResult(targetClass = QueryStoreDTO.class, 
                columns = {
                    @ColumnResult(name = "id", type = Integer.class),
                    @ColumnResult(name = "logo", type = String.class),
                    @ColumnResult(name = "name", type = String.class),
                    @ColumnResult(name = "representative", type = String.class),
                    @ColumnResult(name = "address", type = String.class),
                    @ColumnResult(name = "phone", type = String.class),
                    @ColumnResult(name = "sortdate", type = Date.class)
                }))
public class QueryStoreDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String logo;
    
    private String name;
    
    private String representative;
    
    private String address;
    
    private String phone;
    
    private Date sortdate;
    
}
