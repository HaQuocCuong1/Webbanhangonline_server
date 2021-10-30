/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.repository;

import com.se.webbanhang.entity.Role_user;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ASUS
 */
@Repository
public interface RoleUserRespository extends JpaRepository<Role_user, Integer>{
    Optional<Role_user> findByRole(String roleName);
}
