/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.service;

import com.se.webbanhang.entity.Order_detail;
import com.se.webbanhang.entity.Orders;
import com.se.webbanhang.repository.UsersRespository;
import com.se.webbanhang.entity.Role_user;
import com.se.webbanhang.entity.Store;
import com.se.webbanhang.entity.Users;
import com.se.webbanhang.exception.NotFoundException;
import com.se.webbanhang.service.RoleUserService;
import com.se.webbanhang.service.UsersService;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class UsersServiceImpl implements UsersService{
    @Autowired
    private UsersRespository usersRespository;
    @Autowired
    private RoleUserService roleUserService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public List<Users> findAll() {
        return usersRespository.findAll();
    }

    @Override
    public Users findbyId(int id) {
        Optional<Users> result = usersRespository.findById(id);
        Users theUsers = null;
        if (result.isPresent())
        {
            theUsers = result.get();
        }else {
            throw new NotFoundException("Did not find User id: "+id);
        }
        return theUsers;
    }

    @Override
    public void save(Users theUsers) {
        usersRespository.save(theUsers);
    }

    @Override
    public void delete(int id) {
        usersRespository.deleteById(id);
    }

    @Override
    public List<Role_user> getroles(int userId) {
        Users theUsers = usersRespository.getById(userId);
        List<Role_user> listRoles = null;
        if (theUsers != null)
        {
            listRoles = theUsers.getListroles();
        }
        return listRoles;
    }

    @Override
    public Role_user getRole(int userId, int roleId) {
        Users theUsers = usersRespository.getById(userId);
        if (theUsers != null)
            return theUsers.getRoleUser(roleId);
        else
            return null;
    }

    @Override
    public Optional<Users> findByUsername(String name) {
        return usersRespository.findByUsername(name);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return usersRespository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return usersRespository.existsByEmail(email);
    }

    @Override
    public boolean findByVerificationCode(String code) {
        Users theUsers = usersRespository.findByVerificationCode(code);
        if (theUsers == null)
            return false;
        else
        {
            usersRespository.enable(theUsers.getId());
            return true;
        }
            
    }

    @Override
    public Users findByStoreId(int storeId) {
        Store theStore = storeService.findbyId(storeId);
        Users theUsers = theStore.getUsers();
        return theUsers;
    }

    @Override
    public Integer totalUser(String type) {
        List<Users> users = findAll();
        int count = 0;
        for(Users u : users)
        {
            List<Role_user> roles = u.getListroles();
            for(Role_user role : roles)
            {
                if(role.getRole().equals(type))
                    count++;
            }
        }
        return count;
    }

    @Override
    public Double totalInvenue() {
        double total = 0;
        List<Users> userses = findAll();
        for(Users u : userses)
        {
            List<Role_user> roles = u.getListroles();
            for(Role_user role : roles)
            {
                if(role.getRole().equals("user") || role.getRole().equals("Buyer"))
                {
                    Users theUsers = role.getUsers();
                    List<Orders> listOrder = theUsers.getListorders();
                    for(Orders theOrders: listOrder)
                    {
                        List<Order_detail> od1 = theOrders.getListOrderDetail();
                        for (Order_detail od2 : od1)
                        {
                            total+=od2.getTotalmoney();
                        }
                    }
                }
            }
        }
        return total;
    }

    @Override
    public Boolean confirmUser(int userId) {
        Users theUsers = findbyId(userId);
        if (theUsers == null)
            return false;
        else
        {
            usersRespository.enable(theUsers.getId());
            try {
                sendVerificationOrderEmail(theUsers);
            } catch (MessagingException ex) {
                Logger.getLogger(UsersServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(UsersServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
    }
    public void sendVerificationOrderEmail(Users theUsers) throws MessagingException, UnsupportedEncodingException {
        String subject = "Chúc mừng bạn đã mở tài khoản bán hàng thành công";
        String senderName = "web ban hang online";
        String mailContent = "<p>Hi "+ theUsers.getName()+ ",</p>";
        mailContent += "<p> Bạn đã đăng kí thành công dịch vụ mở shop của chúng tôi giờ đây bạn có thể đăng sản phẩm của mình lên trang web:</p>";
        mailContent += "<h3> THÔNG TIN TÀI KHOẢN</h3>";
        mailContent += "<p> username: "+ theUsers.getUsername()+"</p>";
        mailContent += "<p> phone: "+ theUsers.getPhone()+"</p>";
        mailContent += "<p> phone: "+ theUsers.getEmail()+"</p>";
        mailContent += "<p> Dịch vụ: Mở shop bán hàng </p>";
        mailContent += "<p> Cảm ơn bạn rất nhiều đã tin tưởng sử dụng dịch vụ của chúng tôi. Chúc bạn bán được thật nhiều đơn hàng</p>";
        mailContent += "<p> Mọi chi tiết xin liên hệ qua số điện thoại: 0374858374, Xin cảm ơn :</p>";
        
        mailContent += "<p> Thank you <br/> Web ban hang online</p>";
        try
        {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper hepHelper = new MimeMessageHelper(message, "utf-8");
            hepHelper.setFrom("hacuong6212@gmail.com", senderName);
            hepHelper.setTo(theUsers.getEmail());
            hepHelper.setSubject(subject);
            hepHelper.setText(mailContent, true);
            mailSender.send(message);
        } catch(MessagingException e)
        {
            throw new IllegalStateException("failed to send mail");
        }
    }

    @Override
    public List<Users> findAllUserNotConfirm() {
        List<Users> userses = findAll();
        List<Users> userses2 = new ArrayList<>();
        if(userses != null)
        {
            for(Users u : userses)
            {
                List<Role_user> lsrole = u.getListroles();
                if(u.getStatus() == 0)
                {
                    for(Role_user ru : lsrole)
                    {
                        if(ru.getRole().equals("Buyer"))
                            userses2.add(u);
                    }
                }
            }
        }else
            userses = new ArrayList<>();
        return userses2;
    }

    @Override
    public Integer totalNotConfirm() {
       int total = 0;
       List<Users> lsuser = findAllUserNotConfirm();
       for(Users u : lsuser)
       {
           total++;
       }
       return total;
    }
}
