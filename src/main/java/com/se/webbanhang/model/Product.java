/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.model;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class Product {
    private String name;
    private String code;
    private String sort_description;
    private String detail_description;
    private int ban_nhanh;
    private double price;
    private int featured;
    private double competitive_price;
    private String avartar;
    private Date date_sale;
    private int quantity;
    private int status;
    private int user;
    private int supplier;
    private int categories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSort_description() {
        return sort_description;
    }

    public void setSort_description(String sort_description) {
        this.sort_description = sort_description;
    }

    public String getDetail_description() {
        return detail_description;
    }

    public void setDetail_description(String detail_description) {
        this.detail_description = detail_description;
    }

    public int getBan_nhanh() {
        return ban_nhanh;
    }

    public void setBan_nhanh(int ban_nhanh) {
        this.ban_nhanh = ban_nhanh;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getFeatured() {
        return featured;
    }

    public void setFeatured(int featured) {
        this.featured = featured;
    }

    public double getCompetitive_price() {
        return competitive_price;
    }

    public void setCompetitive_price(double competitive_price) {
        this.competitive_price = competitive_price;
    }

    public String getAvartar() {
        return avartar;
    }

    public void setAvartar(String avartar) {
        this.avartar = avartar;
    }

    public Date getDate_sale() {
        return date_sale;
    }

    public void setDate_sale(Date date_sale) {
        this.date_sale = date_sale;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getSupplier() {
        return supplier;
    }

    public void setSupplier(int supplier) {
        this.supplier = supplier;
    }

    public int getCategories() {
        return categories;
    }

    public void setCategories(int categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Product{" + "name=" + name + ", code=" + code + ", sort_description=" + sort_description + ", detail_description=" + detail_description + ", ban_nhanh=" + ban_nhanh + ", price=" + price + ", featured=" + featured + ", competitive_price=" + competitive_price + ", avartar=" + avartar + ", date_sale=" + date_sale + ", quantity=" + quantity + ", status=" + status + ", user=" + user + ", supplier=" + supplier + ", categories=" + categories + '}';
    }
    
}
