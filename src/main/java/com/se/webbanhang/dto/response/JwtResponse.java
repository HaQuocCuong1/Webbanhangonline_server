/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.dto.response;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author ASUS
 */
public class JwtResponse {
    private int id;
    private String token;
    private String type = "Bearer";
    private String name;
    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse() {
    }

    public JwtResponse(int id, String token, String name, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.token = token;
        this.name = name;
        this.roles = roles;
    }

    public JwtResponse(String token, String name, Collection<? extends GrantedAuthority> roles) {
        this.token = token;
        this.name = name;
        this.roles = roles;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }
    
}
