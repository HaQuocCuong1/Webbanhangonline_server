/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se.webbanhang.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Admin
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Store")
public class Store {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "code")
    private String code;
    
    @NotNull
    @Column(name = "name")
    private String name;
    
    @Column(name = "logo")
    private String logo;
    
    @Column(name = "datestore")
    private Date dateStore;
    
    @OneToOne
    @JsonIgnore
    private Users users;
    
}
