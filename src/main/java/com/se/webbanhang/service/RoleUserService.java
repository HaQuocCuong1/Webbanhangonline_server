/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.service;

import com.se.webbanhang.entity.Role_user;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ASUS
 */
public interface RoleUserService {
    Optional<Role_user> findByRole(String roleName);
    public List<Role_user> findAll();
    public Role_user findbyId(int id);
    public void save(Role_user theRoleUser);
    public void savebyUser(Role_user theRoleUser, int userId);
    public void updatebyUser(Role_user theRoleUser, int userId);
    public void delete(int id);
}
