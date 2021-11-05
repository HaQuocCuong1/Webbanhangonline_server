/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.entity;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "categories")
public class Categories implements Serializable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
    @Column(name = "total_product")
    private int totalproduct;
    
    @NotNull
    @Column(name = "avartar")
    private String avartar;
    
    @OneToMany(mappedBy = "categories", cascade=CascadeType.ALL)
    private List<Products> products;

    public String getAvartar() {
        return avartar;
    }

    public void setAvartar(String avartar) {
        this.avartar = avartar;
    }

    
    public List<Products> getProducts() {
        return products;
    }
    public Products getProduct(int productId)
    {
          if(products!=null)
          {
             for(Products theProduct:products)
                 if(theProduct.getId()== productId)
                     return theProduct;
           }
          return null;
    }
    public void setProducts(List<Products> products) {
        this.products = products;
    }
    public void addProduct(Products theProduct) {
        if (products == null) {
            products = new ArrayList<>(); }
        products.add(theProduct);}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getTotalproduct() {
        return totalproduct;
    }

    public void setTotalproduct() {
        int count = 0;
        for(Products p : products)
        {
            count ++;
        }
        this.totalproduct = count;
    }

    @Override
    public String toString() {
        return "Categories{" + "id=" + id + ", name=" + name + ", code=" + code + ", totalproduct=" + totalproduct + '}';
    }
    
}
