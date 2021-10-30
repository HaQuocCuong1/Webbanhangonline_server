/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.entity;

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

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "Supplier")
public class Supplier {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
    @Column(name = "logo")
    private String logo;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @OneToMany(mappedBy = "supplier", cascade=CascadeType.ALL)
    private List<Products> products;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public String toString() {
        return "Supplier{" + "id=" + id + ", name=" + name + ", code=" + code + ", logo=" + logo + ", email=" + email + ", phone=" + phone + ", address=" + address +  '}';
    }
    
}
