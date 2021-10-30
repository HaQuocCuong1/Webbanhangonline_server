/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.security.userprincal;

import com.se.webbanhang.entity.Users;
import com.se.webbanhang.repository.UsersRespository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class UserDetailService implements UserDetailsService{
    @Autowired
    UsersRespository usersRespository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Users user = usersRespository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found -> username or password"+username));
       return UserPrinciple.built(user);
    }
    
}
