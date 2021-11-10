/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se.webbanhang.rest;

import com.se.webbanhang.dto.request.StoreDTO;
import com.se.webbanhang.entity.Store;
import com.se.webbanhang.entity.Users;
import com.se.webbanhang.service.StoreService;
import com.se.webbanhang.service.UsersService;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
public class StoreRestController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private StoreService storeService;
    
    @GetMapping("/store/user/{userId}")
    public Store findStoreByUser(@PathVariable int userId)
    {
        return storeService.findStoreByUser(userId);
    }
    @PostMapping("/store/user/{userId}")
    public String saveStore(@RequestBody StoreDTO thestoDTO, @PathVariable int userId)
    {
        Store theStore = null;
        if(thestoDTO != null)
        {
            Users theUser = usersService.findbyId(userId);
            if(theUser != null)
            {
                theStore = new Store();
                theStore.setId(0);
                theStore.setName(thestoDTO.getName());
                theStore.setCode(thestoDTO.getCode());
                long millis=System.currentTimeMillis();
                theStore.setDateStore(new Date(millis));
                theStore.setUsers(theUser);
                if(thestoDTO.getLogo() != null)
                {
                    theStore.setLogo(thestoDTO.getLogo());
                }else{
                    theStore.setLogo("https://res.cloudinary.com/devatchannel/image/upload/v1602752402/avatar/avatar_cugq40.png?fbclid=IwAR1WVZLl0dmt01nM1K4-Lvy30w1-p5XOS7qATZNA7udT-Heak0NA9MvQnys");
                }
            }
            else{
                throw new RuntimeException("User id not found - " + userId);
            }
        }
        storeService.save(theStore);
        return "Tạo shop thành công";
    }
}