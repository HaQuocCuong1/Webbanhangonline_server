/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.dto.response;

/**
 *
 * @author ASUS
 */
public class ReponseMessage {
    private String message;

    public ReponseMessage() {
    }

    public ReponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
