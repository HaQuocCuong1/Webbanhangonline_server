/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.rest;

import com.se.webbanhang.repository.RoleUserRespository;
import com.se.webbanhang.entity.Role_user;
import com.se.webbanhang.entity.Users;
import com.se.webbanhang.service.RoleUserService;
import com.se.webbanhang.service.UsersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/api")
public class RoleUserRestController {
    @Autowired
    private RoleUserService roleUserService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private RoleUserRespository roleUserRespository;
    @GetMapping("/roles")
    public List<Role_user> findAll()
    {
        return roleUserService.findAll();
    }
    @PostMapping("/roles/users/{userId}")
    public Role_user saveRolebyUserId(@RequestBody Role_user theRoleuser, @PathVariable int userId)
    {
        Users theUsers = usersService.findbyId(userId);
        theRoleuser.setId(0);
        theRoleuser.setUsers(theUsers);
        theUsers.addRoleUser(theRoleuser);
        roleUserService.save(theRoleuser);
        return theRoleuser;
    }
    @PutMapping("/roles/users/{userId}")
    public Role_user update(@RequestBody Role_user theRoleuser, @PathVariable int userId)
    {
        Users theUsers = usersService.findbyId(userId);
        theRoleuser.setUsers(theUsers);
        roleUserService.save(theRoleuser);
        return theRoleuser;
    }
    @GetMapping("/roles/{roleId}")
    public Role_user findByid(@PathVariable int roleId)
    {
        Role_user theRoleuser = roleUserService.findbyId(roleId);
        if (theRoleuser == null)
        {
            throw new RuntimeException("Role not find by "+ roleId);
        }
        return theRoleuser;
    }
    @DeleteMapping("/roles/{roleId}")
    public String delete (@PathVariable int roleId)
    {
        Role_user tempRoleuser = roleUserService.findbyId(roleId);
        if (tempRoleuser == null)
        {
            throw new RuntimeException("Role id not found - " + roleId);
        } else {
           //roleUserService.delete(roleId);
           roleUserRespository.deleteById(roleId);
        }
        return "Delete role Id: "+roleId;
    }
}
