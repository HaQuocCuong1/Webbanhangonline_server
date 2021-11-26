/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.se.webbanhang.service;

import com.se.webbanhang.entity.Comment;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface CommentService {
    public List<Comment> findAll();
    public Comment findbyId(int id);
    public void save(Comment theComment);
    public void delete(int id);
    public List<Comment> findALLByProductId(int productId);
}
