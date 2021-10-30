/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package com.se.webbanhang.rest;

import com.se.webbanhang.entity.Order_detail;
import com.se.webbanhang.service.TransactionsService;
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


@RestController
@RequestMapping("/api")
public class TransactionRestController {
    private TransactionsService transactionsService;
    @Autowired
    public TransactionRestController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }
    @GetMapping("/transactions")
    public List<Order_detail> findAll()
    {
        return transactionsService.findAll();
    }
//    @GetMapping("/categories/{categoriesId}")
//    public Categories findByid(@PathVariable int categoriesId)
//    {
//        Categories thecate = categoriesService.findbyId(categoriesId);
//        if (thecate == null)
//        {
//            throw new RuntimeException("Categories not find by "+ categoriesId);
//        }
//        return thecate;
//    }
    @PostMapping("/transactions")
    public Order_detail save(@RequestBody Order_detail theTransactions)
    {
        theTransactions.setId(0);
        transactionsService.save(theTransactions);
        return theTransactions;
    }
    @PutMapping("/transactions")
    public Order_detail update(@RequestBody Order_detail theTransactions)
    {
        transactionsService.save(theTransactions);
        return theTransactions;
    }
    @DeleteMapping("/transactions/{transactionId}")
    public String delete (@PathVariable int transactionId)
    {
        Order_detail tempTransactions = transactionsService.findbyId(transactionId);
        if (tempTransactions == null)
        {
            throw new RuntimeException("transaction id not found - " + transactionId);
        } else {
            transactionsService.delete(transactionId);
        }
        return "Delete transaction Id: "+transactionId;
    }
}*/
