/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.service;

import com.se.webbanhang.entity.Order_detail;
import com.se.webbanhang.repository.OrderRespository;
import com.se.webbanhang.entity.Orders;
import com.se.webbanhang.entity.Products;
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
    private OrderDetailService orderDetailService;
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
       List<Order_detail> listOd = theOrders.getListOrderDetail();
       Users theUsers2 = theOrders.getUsers();
       if(theOrders == null)
           return false;
       else
       {
           oderRespository.updateStatusOrder(theOrders.getId());
           for(Order_detail od : listOd)
           {
               if(od != null)
               {
                   Products p = od.getProducts();
                   Users theUsers1 = p.getUser();
                   try {
                       sendReceiveOrder(theUsers1, theUsers2, theOrders);
                   } catch (MessagingException ex) {
                       Logger.getLogger(OrderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (UnsupportedEncodingException ex) {
                       Logger.getLogger(OrderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
           }
           return true;
       }
    }
    public void sendReceiveOrder(Users theUsers1, Users theUsers2, Orders theOrders) throws MessagingException, UnsupportedEncodingException {
        String subject = "Th??ng tin nh???n ????n h??ng c???a b???n";
        String senderName = "web ban hang online";
        String mailContent = "<p>Hi "+ theUsers1.getName()+ ",</p>";
        mailContent += "<p> Ch??c m???ng b???n, s???n ph???m c???a b???n ???? ???????c m???t ai ???? mua:</p>";
        mailContent += "<h3> TH??NG TIN ????N H??NG</h3>";
        mailContent += "<p> M?? ????n h??ng: "+ theOrders.getId() +"</p>";
        mailContent += "<p> D???ch v???: ?????T H??NG TR???C TUY???N </p>";
        mailContent += "<h3> TH??NG TIN NG?????I NH???N</h3>";
        mailContent += "<p> T??n ng?????i nh???n: "+ theOrders.getNamecustomer()+"</p>";
        mailContent += "<p> Email: "+ theOrders.getEmail()+"</p>";
        mailContent += "<p> ?????a ch??? mua h??ng: "+ theOrders.getDeliveryaddress()+"</p>";
        mailContent += "<p> S??? ??i???n tho???i: "+ theOrders.getPhone()+"</p>";
        int payments = theOrders.getPayments();
        if (payments == 1)
        {
            mailContent += "<p> H??nh th???c thanh to??n: TI???N M???T</p>";
        }
        if (payments == 0)
        {
            mailContent += "<p> H??nh th???c thanh to??n: ONLINE</p>";
        }
        mailContent += "<h3> S???N PH???M ???? ?????T</h3>";
        mailContent += "<table style='width: 600px'>";
        mailContent += "<thead>";
        mailContent += "<th>S???n ph???m</th>";
        mailContent += "<th>S??? l?????ng</th>";
        mailContent += "<th>Gi?? ti???n</th>";
        mailContent += "</thead>";
        mailContent += "<tbody>";
        double total = 0;
        
        for(Order_detail od : theOrders.getListOrderDetail())
        {
            if(od.getProducts().getUser().getId() == theUsers1.getId())
            {
                mailContent += "<tr>";
                mailContent += "<td>"+ od.getName()+ "</td>";
                mailContent += "<td>"+ od.getQuantity() +"</td>";
                mailContent += "<td>"+ od.getTotalmoney() + "</td>";
                mailContent += "</tr>";
                total += od.getTotalmoney();
            }
        }
        mailContent += "<tr>";
        mailContent += "<td></td>";
        mailContent += "<td>T???ng ti???n thanh to??n:</td>";
        mailContent += "<td>"+ total + "vnd</td>";
        mailContent += "</tr>";
        mailContent += "</tbody>";
        mailContent += "</table>";
        mailContent += "<p> M???i chi ti???t xin li??n h??? qua s??? ??i???n tho???i: 0374858374, Xin c???m ??n qu?? kh??ch ???? s??? d???ng d???ch v??? c???a ch??ng t??i:</p>";
        
        mailContent += "<p> Thank you <br/> Web ban hang online</p>";
        try
        {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper hepHelper = new MimeMessageHelper(message, "utf-8");
            hepHelper.setFrom("hacuong6212@gmail.com", senderName);
            hepHelper.setTo(theUsers1.getEmail());
            hepHelper.setSubject(subject);
            hepHelper.setText(mailContent, true);
            mailSender.send(message);
        } catch(MessagingException e)
        {
           throw new IllegalStateException("failed to send mail", e);
        }
    }
    public void sendVerificationOrderEmail(Orders theOrders) throws MessagingException, UnsupportedEncodingException {
        String subject = "Th??ng tin ????n h??ng c???a b???n";
        String senderName = "web ban hang online";
        String mailContent = "<p>Hi "+ theOrders.getNamecustomer()+ ",</p>";
        mailContent += "<p> B???n ho???c m???t ai ???? ???? ????ng k?? d???ch v??? t???i shop v???i th??ng tin nh?? sau:</p>";
        mailContent += "<h3> TH??NG TIN ????N H??NG</h3>";
        mailContent += "<p> M?? ????n h??ng: "+ theOrders.getId() +"</p>";
        mailContent += "<p> D???ch v???: ?????T H??NG TR???C TUY???N </p>";
        mailContent += "<h3> TH??NG TIN NG?????I NH???N</h3>";
        mailContent += "<p> T??n ng?????i nh???n: "+ theOrders.getNamecustomer()+"</p>";
        mailContent += "<p> Email: "+ theOrders.getEmail()+"</p>";
        mailContent += "<p> ?????a ch??? mua h??ng: "+ theOrders.getDeliveryaddress()+"</p>";
        mailContent += "<p> S??? ??i???n tho???i: "+ theOrders.getPhone()+"</p>";
        int payments = theOrders.getPayments();
        if (payments == 1)
        {
            mailContent += "<p> H??nh th???c thanh to??n: TI???N M???T</p>";
        }
        if (payments == 0)
        {
            mailContent += "<p> H??nh th???c thanh to??n: ONLINE</p>";
        }
        mailContent += "<h3> S???N PH???M ???? ?????T</h3>";
        mailContent += "<table style='width: 600px'>";
        mailContent += "<thead>";
        mailContent += "<th>S???n ph???m</th>";
        mailContent += "<th>S??? l?????ng</th>";
        mailContent += "<th>Gi?? ti???n</th>";
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
        mailContent += "<td>T???ng ti???n thanh to??n:</td>";
        mailContent += "<td>"+ total + "vnd</td>";
        mailContent += "</tr>";
        mailContent += "</tbody>";
        mailContent += "</table>";
        mailContent += "<p> M???i chi ti???t xin li??n h??? qua s??? ??i???n tho???i: 0374858374, Xin c???m ??n qu?? kh??ch ???? ?????t h??ng:</p>";
        
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

    @Override
    public Orders listOrdersByOrderdetailId(int orderdetailId) {
        Order_detail od = orderDetailService.findbyId(orderdetailId);
        Orders theOrders = null;
        if(od != null)
        {
            theOrders = od.getOrders();
        }else
            throw new NotFoundException("Did not find Orderdetail id: "+orderdetailId);
        return theOrders;
    }
}
