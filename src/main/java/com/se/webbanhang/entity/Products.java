/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.webbanhang.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "Products")
public class Products implements Serializable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @NotNull
    @NotBlank(message = "Name is is madatory")
    private String name;
    @Column(name = "code")
    private String code;
    @Column(name = "sort_description")
    private String sort_description;
    @Column(name = "detail_description")
    private String detail_description;
    @Column(name = "ban_nhanh")
    private int ban_nhanh;
    @NotNull
    @Column(name = "price")
    private double price;
    @NotNull
    @Column(name = "featured")
    private int featured;
    @Column(name = "competitive_price")
    private double competitive_price;
    @NotNull
    @Column(name = "avartar")
    private String avartar;
    @NotNull
    @Column(name = "date_sale")
    private Date date_sale;
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "promotion")
    private int promotion;
    @NotNull
    @Column(name = "status")
    private int status;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "Users_id", foreignKey = @ForeignKey)
    private Users user;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JsonIgnore
    private Supplier supplier;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JsonIgnore
    private Categories categories;
    
    @OneToMany(mappedBy = "products",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comments;
    
    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private List<Picture_product> listPictureproduct;
    @OneToMany(mappedBy = "products", cascade = CascadeType.REFRESH)
    private List<Order_detail> listOrderDetail;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
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

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }
    
    @JsonIgnore
    public Users getUsers() {
        return user;
    }
    @JsonIgnore
    public void setUsers(Users users) {
        this.user = user;
    }
    @JsonIgnore
    public Supplier getSupplier() {
        return supplier;
    }
    @JsonIgnore
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    @JsonIgnore
    public Users getUser() {
        return user;
    }
    @JsonIgnore
    public void setUser(Users user) {
        this.user = user;
    }
    @JsonIgnore
    public Categories getCategories() {
        return categories;
    }
    @JsonIgnore
    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public int getStatus() {
        return status;
    }

    public List<Order_detail> getListOrderDetail() {
        return listOrderDetail;
    }

    public void setListOrderDetail(List<Order_detail> listOrderDetail) {
        this.listOrderDetail = listOrderDetail;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    
    public List<Picture_product> getListPictureproduct() {
        return listPictureproduct;
    }
    public Picture_product getPictureProduct(int PrProductId)
    {
          if(listPictureproduct != null)
          {
             for(Picture_product thePrProduct:listPictureproduct)
                 if(thePrProduct.getId()== PrProductId)
                     return thePrProduct;
           }
          return null;
    }
    public void setListPictureproduct(List<Picture_product> listPictureproduct) {
        this.listPictureproduct = listPictureproduct;
    }
    public void addPrProduct(Picture_product thePrProduct) {
        if (listPictureproduct == null) {
            listPictureproduct = new ArrayList<>(); }
        listPictureproduct.add(thePrProduct);
    }
    @Override
    public String toString() {
        return "Products{" + "id=" + id + ", name=" + name + ", code=" + code + ", sort_description=" + sort_description + ", detail_description=" + detail_description + ", ban_nhanh=" + ban_nhanh + ", price=" + price + ", featured=" + featured + ", competitive_price=" + competitive_price + ", avartar=" + avartar + ", date_sale=" + date_sale + ", quantity=" + quantity + ", status=" + status + ", user=" + user + ", supplier=" + supplier + ", categories=" + categories + '}';
    }
    
}
