/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Orders")
public class Orders {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @Column(name = "payments")
    private int payments;
    
    @NotNull
    @Column(name = "deliveryaddress")
    private String deliveryaddress;
    
    @Column(name = "transportfee")
    private double transportfee;
    
    @NotNull
    @Column(name = "status") 
    private int status;
    
    @Column(name = "dateorder")
    private Date dateorder;
    
    @Column(name = "namecustomer")
    private String namecustomer;
    
    @NotNull
    @Column(name = "email")
    private String email;
    
    @Size(min = 9, max = 10)
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "seller_id")
    private int sellerId;
    
    @Column(name = "note")
    private String note;
    
    
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Users users;
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<Order_detail> listOrderDetail;

    public Orders(Date dateorder,int payments, String deliveryaddress, double transportfee, String namecustomer, String email, String phone, String note) {
        this.dateorder = dateorder;
        this.payments = payments;
        this.deliveryaddress = deliveryaddress;
        this.transportfee = transportfee;
        this.namecustomer = namecustomer;
        this.email = email;
        this.phone = phone;
        this.note = note;
        this.sellerId = 1;
    }

    
}
