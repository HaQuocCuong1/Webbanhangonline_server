/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.service;

import com.se.webbanhang.entity.Order_detail;
import com.se.webbanhang.repository.OrderRespository;
import com.se.webbanhang.entity.Orders;
import com.se.webbanhang.entity.Users;
import com.se.webbanhang.exception.NotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
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


@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRespository oderRespository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public List<Orders> findAll() {
        return oderRespository.findAll();
    }

    @Override
    public Orders findbyId(int id) {
        Optional<Orders> result = oderRespository.findById(id);
        Orders theOrders = null;
        if (result.isPresent())
        {
            theOrders = result.get();
        }else {
            throw new NotFoundException("Did not find Order id: "+id);
        }
        return theOrders;
    }

    @Override
    public void save(Orders theOrders) {
        oderRespository.save(theOrders);
        try {
            sendVerificationOrderEmail(theOrders);
        } catch (MessagingException ex) {
            Logger.getLogger(OrderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(OrderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        oderRespository.deleteById(id);
    }

    @Override
    public boolean updateStatusOrder(int id) {
       Orders theOrders = findbyId(id);
       if(theOrders == null)
           return false;
       else
       {
           oderRespository.updateStatusOrder(theOrders.getId());
           return true;
       }
    }
    public void sendVerificationOrderEmail(Orders theOrders) throws MessagingException, UnsupportedEncodingException {
        String subject = "Thông tin đơn hàng của bạn";
        String senderName = "web ban hang online";
        String mailContent = "<p>Hi "+ theOrders.getNamecustomer()+ ",</p>";
        mailContent += "<p> Bạn hoặc một ai đó đã đăng kí dịch vụ tại shop với thông tin như sau:</p>";
        mailContent += "<h3> THÔNG TIN ĐƠN HÀNG</h3>";
        mailContent += "<p> Mã đơn hàng: "+ theOrders.getId() +"</p>";
        mailContent += "<p> Dịch vụ: ĐẶT HÀNG TRỰC TUYẾN </p>";
        mailContent += "<h3> THÔNG TIN NGƯỜI NHẬN</h3>";
        mailContent += "<p> Tên người nhận: "+ theOrders.getNamecustomer()+"</p>";
        mailContent += "<p> Email: "+ theOrders.getEmail()+"</p>";
        mailContent += "<p> Địa chỉ mua hàng: "+ theOrders.getDeliveryaddress()+"</p>";
        mailContent += "<p> Số điện thoại: "+ theOrders.getPhone()+"</p>";
        int payments = theOrders.getPayments();
        if (payments == 1)
        {
            mailContent += "<p> Hình thức thanh toán: TIỀN MẶT</p>";
        }
        if (payments == 0)
        {
            mailContent += "<p> Hình thức thanh toán: ONLINE</p>";
        }
        mailContent += "<h3> SẢN PHẨM ĐÃ ĐẶT</h3>";
        mailContent += "<table style='width: 600px'>";
        mailContent += "<thead>";
        mailContent += "<th>Sản phẩm</th>";
        mailContent += "<th>Số lượng</th>";
        mailContent += "<th>Giá tiền</th>";
        mailContent += "</thead>";
        mailContent += "<tbody>";
        double total = 0; 
        for(Order_detail od : theOrders.getListOrderDetail())
        {
            mailContent += "<tr>";
            mailContent += "<td>"+ od.getName()+ "</td>";
            mailContent += "<td>"+ od.getQuantity() +"</td>";
            mailContent += "<td>"+ od.getTotalmoney() + "</td>";
            mailContent += "</tr>";
            total += od.getTotalmoney();
        }
        mailContent += "<tr>";
        mailContent += "<td></td>";
        mailContent += "<td>Tổng tiền thanh toán:</td>";
        mailContent += "<td>"+ total + "vnd</td>";
        mailContent += "</tr>";
        mailContent += "</tbody>";
        mailContent += "</table>";
        mailContent += "<p> Mọi chi tiết xin liên hệ qua số điện thoại: 0374858374, Xin cảm ơn quý khách đã đặt hàng:</p>";
        
        mailContent += "<p> Thank you <br/> Web ban hang online</p>";
        try
        {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper hepHelper = new MimeMessageHelper(message, "utf-8");
            hepHelper.setFrom("hacuong6212@gmail.com", senderName);
            hepHelper.setTo(theOrders.getEmail());
            hepHelper.setSubject(subject);
            hepHelper.setText(mailContent, true);
            mailSender.send(message);
        } catch(MessagingException e)
        {
           throw new IllegalStateException("failed to send mail", e);
        }
    }
}
