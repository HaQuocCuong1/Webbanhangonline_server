/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.repository;

import com.se.webbanhang.entity.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ASUS
 */
@Repository
@Transactional
public interface UsersRespository extends JpaRepository<Users, Integer>{
    Optional<Users> findByUsername(String name);
    Boolean existsByUsername(String username);//Kiem tra xem co ton tai user trong db ko
    Boolean existsByEmail(String email);//Kiem tra xem co ton tai email trong db ko
    @Query("SELECT u FROM Users u WHERE u.verificationCode = ?1")
    public Users findByVerificationCode(String code);
    @Query("UPDATE Users u SET u.status = 1 WHERE u.id = ?1")
    @Modifying
    public void enable(int id);
}
