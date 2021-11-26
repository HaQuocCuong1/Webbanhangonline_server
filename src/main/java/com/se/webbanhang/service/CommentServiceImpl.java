/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se.webbanhang.service;

import com.se.webbanhang.entity.Comment;
import com.se.webbanhang.entity.Products;
import com.se.webbanhang.exception.NotFoundException;
import com.se.webbanhang.repository.CommentRespository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRespository commentRespository;
    @Autowired
    private ProductService productService;
    @Override
    public List<Comment> findAll() {
        return commentRespository.findAll();
    }

    @Override
    public Comment findbyId(int id) {
        Optional<Comment> result = commentRespository.findById(id);
        Comment comment = null;
        if(result.isPresent())
        {
            comment = result.get();
        }
        return comment;
    }

    @Override
    public void save(Comment theComment) {
        commentRespository.save(theComment);
    }

    @Override
    public void delete(int id) {
       Comment theComment = findbyId(id);
       if(theComment != null)
           commentRespository.delete(theComment);
       else
           throw new NotFoundException("Not found Comment Id :"+id);
    }

    @Override
    public List<Comment> findALLByProductId(int productId) {
        Products theProducts = productService.findById(productId);
        List<Comment> comments = new ArrayList<>();
        if(theProducts != null)
        {
            List<Comment> listcomments = theProducts.getComments();
            for(Comment c : listcomments)
            {
                comments.add(c);
            }
        }
        return comments;
    }
   
}
