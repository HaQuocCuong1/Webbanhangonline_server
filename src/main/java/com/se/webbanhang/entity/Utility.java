/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.entity;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author ASUS
 */
public class Utility {
    public static String getSiteURL(HttpServletRequest request)    
    {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
