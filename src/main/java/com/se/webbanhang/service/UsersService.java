/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.service;

import com.se.webbanhang.entity.Role_user;
import com.se.webbanhang.entity.Users;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ASUS
 */
public interface UsersService {
    public List<Users> findAll();
    public Users findbyId(int id);
    Optional<Users> findByUsername(String name);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    public void save(Users theUsers);
    public void delete(int id);
    public List<Role_user> getroles(int userId);
    public Role_user getRole(int userId, int roleId);
    public boolean findByVerificationCode(String code);
    public Users findByStoreId(int storeId);
    public Integer totalUser(String user);
    public Double totalInvenue();
    public Boolean confirmUser(int userId);
    public List<Users> findAllUserNotConfirm();
    public Integer totalNotConfirm();
}
