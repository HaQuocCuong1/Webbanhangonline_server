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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "Users")
public class Users {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "username")
    private String username;
    @Column(name = "phone")
    @NotNull
    @Size(min = 9, max = 10)
    private String phone;
    @Column(name = "email")
    @Size(max = 50)
    @Email
    private String email;
    @Column(name = "password")
    @NotNull
    @JsonIgnore
    @Size(min = 6, max = 100, message = "password should have at least 6 characters")
    private String password;
    @Column(name = "avartar")
    @Lob
    private String avartar;
    @Column(name = "address")
    private String address;
    @Column(name = "status")
    private int status;
    @Column(name = "verification_code", updatable = false)
    private String verificationCode;
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Role_user> listroles;
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Orders> listorders;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Products> products;
    @OneToOne(mappedBy = "users")
    private Store store;
    public Users() {
    }
    
    public Users( @Size(min = 3, max = 50)String name, 
            @Size(min = 3, max = 50)String username, 
            @NotNull @NotBlank @Size(min = 9, max = 10)String phone, 
            @Size(max = 50) @Email String email, 
            @NotNull @NotBlank @Size(min = 6, max = 100) String encode,
            @NotNull String address,
            String avartar
    ) {
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = encode;
        this.address = address;
        this.avartar = avartar;
    }
    
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvartar() {
        return avartar;
    }

    public void setAvartar(String avartar) {
        this.avartar = avartar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Role_user> getListroles() {
        return listroles;
    }
    public Role_user getRoleUser(int id)
    {
          if(listroles!=null)
          {
             for(Role_user theRole_user:listroles)
                 if(theRole_user.getId()== id)
                     return theRole_user;
           }
          return null;
    }
    public void setListroles(List<Role_user> listroles) {
        this.listroles = listroles;
    }
    public void addRoleUser(Role_user theRoleuser) {
        if (listroles == null) {
            listroles = new ArrayList<>(); }
        listroles.add(theRoleuser);
    }
    public void removeRoleUser(Role_user theRoleuser) {
        if (theRoleuser != null)
        {
            listroles.remove(theRoleuser);
        }
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public List<Orders> getListorders() {
        return listorders;
    }
    
//    public Orders getOrders(int id)
//    {
//          if(listorders!=null)
//          {
//             for(Orders theOrders:listorders)
//                 if(theOrders.getId()== id)
//                     return theOrders;
//           }
//          return null;
//    }
    public void setListorders(List<Orders> listorders) {
        this.listorders = listorders;
    }
    
//    public void addtOrder(Orders theOrders) {
//        if (listorders == null) {
//            listorders = new ArrayList<>(); }
//        listorders.add(theOrders);
//    }
    
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
        products.add(theProduct);
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", name=" + name + ", username=" + username + ", phone=" + phone + ", email=" + email + ", password=" + password + ", avartar=" + avartar + ", address=" + address + ", status=" + status + ", products=" + products + '}';
    }
    
    
}
