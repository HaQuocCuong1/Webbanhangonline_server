/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "Order_detail")
public class Order_detail {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @Column(name = "totalmoney")
    private double totalmoney;
    
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    
    @Column(name = "discount")
    private double discount;
  
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Orders orders;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Products products;

    public Order_detail(double totalmoney, int quantity, double discount) {
        this.totalmoney = totalmoney;
        this.quantity = quantity;
        this.discount = discount;
    }
    
    
    
}
