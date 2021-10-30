/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.security.userprincal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.se.webbanhang.entity.Users;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author ASUS
 */
public class UserPrinciple implements UserDetails{
    private int id;
    private String name;
    private String username;
    private String phone;
    private String email;
    @JsonIgnore
    private String password;
    private String avartar;
    private String address;
    private int status;
    private Collection<? extends GrantedAuthority> roles;
    public UserPrinciple() {
        
    }
    public UserPrinciple(int id, String name,String username, String phone, String email, String password, String avartar, String address, int status, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.avartar = avartar;
        this.address = address;
        this.status = status;
        this.roles = roles;
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
    public static UserPrinciple built(Users users)
    {
       List<GrantedAuthority> authorities = users.getListroles().stream().map(role-> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
        return new UserPrinciple(users.getId(), users.getName(), users.getUsername(),users.getPhone(), users.getEmail(), 
                users.getPassword(), users.getAvartar(), users.getAddress(), 
                users.getStatus(), authorities);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
