/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.rest;

import com.se.webbanhang.dto.request.SignInForm;
import com.se.webbanhang.dto.request.SignUpForm;
import com.se.webbanhang.dto.response.JwtResponse;
import com.se.webbanhang.dto.response.ReponseMessage;
import com.se.webbanhang.entity.Role_user;
import com.se.webbanhang.entity.Users;
import com.se.webbanhang.entity.Utility;
import com.se.webbanhang.exception.ApiRequestException;
import com.se.webbanhang.exception.NotFoundException;
import com.se.webbanhang.security.jwt.JwtProvider;
import com.se.webbanhang.security.userprincal.UserPrinciple;
import com.se.webbanhang.service.RoleUserService;
import com.se.webbanhang.service.UsersService;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class UsersRestController {
    private UsersService usersService;
    @Autowired
    private RoleUserService roleUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JavaMailSender mailSender;
    public UsersRestController(UsersService usersService) {
        this.usersService = usersService;
    }
    final static String AVARTAR_DEFAULT = "https://res.cloudinary.com/devatchannel/image/upload/v1602752402/avatar/avatar_cugq40.png?fbclid=IwAR1WVZLl0dmt01nM1K4-Lvy30w1-p5XOS7qATZNA7udT-Heak0NA9MvQnys";
    
    @GetMapping("/users")
    public List<Users> findAll()
    {
        return usersService.findAll();
    }
    @GetMapping("/users/{userId}")
    public Users findByid(@PathVariable int userId)
    {
        Users theUsers = usersService.findbyId(userId);
        if (theUsers == null)
        {
            throw new NotFoundException("Users not find by "+ userId);
        }
        return theUsers;
    }
    @GetMapping("/users/{userId}/roles/{roleId}")
    public Role_user findByrole(@PathVariable int userId, @PathVariable int roleId)
    {
        Role_user theRole = usersService.getRole(userId, roleId);
        if (theRole == null)
        {
            throw new NotFoundException("Role not find by "+ roleId);
        }
        return theRole;
    }
    @GetMapping("/users/{userId}/roles")
    public List<Role_user> findAllRolebyUserId(@PathVariable int userId)
    {
        List<Role_user> theRoles = usersService.getroles(userId);
        if (theRoles == null)
        {
            throw new NotFoundException("Role not find by "+ userId);
        }
        return theRoles;
    }
    @PostMapping("/sigup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException
    {
        if (usersService.existsByUsername(signUpForm.getUsername()))
        {
            throw new ApiRequestException("Username exists! Please try again");
        }
        if (usersService.existsByEmail(signUpForm.getEmail()))
        {
            //return new ResponseEntity<>(new ReponseMessage("Email đã tồn tại! Vui lòng thử lại!"), HttpStatus.OK);
            throw new ApiRequestException("Email exists! Please try again");
        }
        Users theUsers = new Users(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getPhone(), signUpForm.getEmail(), passwordEncoder.encode(signUpForm.getPassword()), signUpForm.getAddress(), AVARTAR_DEFAULT);
        theUsers.setId(0);
        String ramdomCode = RandomString.make(64);
        theUsers.setVerificationCode(ramdomCode);
        usersService.save(theUsers);
        Role_user roles = new Role_user("user", theUsers);
        roles.setId(0);
        theUsers.addRoleUser(roles);
        roleUserService.save(roles);
        String siteURL = Utility.getSiteURL(request);
        sendVerificationEmail(theUsers, siteURL);
        return new ResponseEntity<>(new ReponseMessage("Create user message"),HttpStatus.OK);
    }
    @PostMapping("/seller/sigup")
    public ResponseEntity<?> registerSeller(@Valid @RequestBody SignUpForm signUpForm, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException
    {
        if (usersService.existsByUsername(signUpForm.getUsername()))
        {
            throw new ApiRequestException("Username exists! Please try again");
        }
        if (usersService.existsByEmail(signUpForm.getEmail()))
        {
            throw new ApiRequestException("Email exists! Please try again");
            //return new ResponseEntity<>(new ReponseMessage("The email existed! Please try agin!"), HttpStatus.OK);
        }
    
        Users theUsers = new Users(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getPhone(), signUpForm.getEmail(), passwordEncoder.encode(signUpForm.getPassword()), signUpForm.getAddress(), AVARTAR_DEFAULT);
        theUsers.setId(0);
        String ramdomCode = RandomString.make(64);
        theUsers.setVerificationCode(ramdomCode);
        usersService.save(theUsers);
        Role_user roles = new Role_user("Buyer", theUsers);
        roles.setId(0);
        theUsers.addRoleUser(roles);
        roleUserService.save(roles);
        String siteURL = Utility.getSiteURL(request);
        sendVerificationEmail(theUsers, siteURL);
        return new ResponseEntity<>(new ReponseMessage("Create user message"),HttpStatus.OK);
    }
    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok( new JwtResponse(userPrinciple.getId(),token, userPrinciple.getName(), userPrinciple.getAuthorities()));
    }
    @PutMapping("/users")
    public Users update(@RequestBody Users theUsers)
    {
        Users theUsers1 = usersService.findbyId(theUsers.getId());
        theUsers.setUsername(theUsers1.getUsername());
        theUsers.setPassword(theUsers1.getPassword());
        usersService.save(theUsers);
        System.out.println(""+theUsers);
        return theUsers;
    }
    @DeleteMapping("/users/{userId}")
    public String delete (@PathVariable int userId)
    {
        Users tempUsers = usersService.findbyId(userId);
        if (tempUsers == null)
        {
            throw new NotFoundException("User id not found - " + userId);
        } else {
            usersService.delete(userId);
        }
        return "Delete users Id: "+userId;
    }
    @GetMapping("/verify")
    public String verifiAccount(@Param("code") String code)
    {
        boolean verified = usersService.findByVerificationCode(code);
        String pageTitle = verified ? "Verification Successded!" : "Verification Faile";
        return pageTitle;
    }
    public void sendVerificationEmail(Users theUsers, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String subject = "Please verify your registrantion";
        String senderName = "web ban hang online";
        String mailContent = "<p>Dear "+ theUsers.getName() + ",</p>";
        mailContent += "<p> Please click the link below to verify to your registration:</p>";
        String verifyURL = siteURL + "/api/verify?code="+theUsers.getVerificationCode();
         mailContent += "<h3><a href=\"" + verifyURL + "\">VERIFY</a></h3>";
        
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
}
