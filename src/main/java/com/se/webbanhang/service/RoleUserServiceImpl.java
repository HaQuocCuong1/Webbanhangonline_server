/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.service;

import com.se.webbanhang.repository.RoleUserRespository;
import com.se.webbanhang.entity.Role_user;
import com.se.webbanhang.entity.Users;
import com.se.webbanhang.exception.NotFoundException;
import com.se.webbanhang.service.RoleUserService;
import com.se.webbanhang.service.UsersService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class RoleUserServiceImpl implements RoleUserService{
  
    private RoleUserRespository roleUserRespository;
  
    private UsersService usersService;

    @Autowired
    public RoleUserServiceImpl(RoleUserRespository roleUserRespository, UsersService usersService) {
        this.roleUserRespository = roleUserRespository;
        this.usersService = usersService;
    }
    
    
    
    
    @Override
    public List<Role_user> findAll() {
        return roleUserRespository.findAll();
    }

    @Override
    public Role_user findbyId(int id) {
        Optional<Role_user> result = roleUserRespository.findById(id);
        Role_user theRole_user = null;
        if (result.isPresent())
        {
            theRole_user = result.get();
        }else {
            throw new NotFoundException("Did not find role id: "+id);
        }
        return theRole_user;
    }

    @Override
    public void save(Role_user theRoleUser) {
        roleUserRespository.save(theRoleUser);
    }

    @Override
    public void delete(int id) {
        roleUserRespository.deleteById(id);
    }

    @Override
    public void savebyUser(Role_user theRoleUser, int userId) {
        Users theUsers= usersService.findbyId(userId);
        theUsers.addRoleUser(theRoleUser);
        roleUserRespository.save(theRoleUser);
    }

    @Override
    public void updatebyUser(Role_user theRoleUser, int userId) {
        Users theUser = usersService.findbyId(userId);
        Role_user theRole = theUser.getRoleUser(theRoleUser.getId());
        setValueRoleUser(theRole, theRoleUser);
         roleUserRespository.save(theRole);
    }
     private void setValueRoleUser(Role_user theRole, Role_user tempRole)
    {
        theRole.setRole(tempRole.getRole());
       
    }

    @Override
    public Optional<Role_user> findByRole(String roleName) {
        return roleUserRespository.findByRole(roleName);
    }
}
