/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.dto.request;

import com.se.webbanhang.entity.Role_user;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class SignUpForm {
    private String name;
    private String username;
    private String phone;
    private String email;
    private String password;
    private String address;
    public SignUpForm() {
    }

    public SignUpForm(String name, String username, String phone, String email, String password, String address) {
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
