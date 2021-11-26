
package com.se.webbanhang.rest;

import com.se.webbanhang.dto.request.CommentDTO;
import com.se.webbanhang.entity.Comment;
import com.se.webbanhang.entity.Products;
import com.se.webbanhang.entity.Users;
import com.se.webbanhang.exception.NotFoundException;
import com.se.webbanhang.service.CommentService;
import com.se.webbanhang.service.ProductService;
import com.se.webbanhang.service.UsersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
public class CommentRestController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UsersService usersService;
    
    @GetMapping("/comments")
    public List<Comment> findAll()
    {
        return commentService.findAll();
    }
    @GetMapping("/comments/product/{productId}")
    public List<Comment> findALLByProductId(@PathVariable int productId)
    {
        return commentService.findALLByProductId(productId);
    }
    @PostMapping("/comments")
    public Comment saveComment(@RequestBody CommentDTO commentDTO)
    {
        int productId = commentDTO.getProductId();
        Products theProducts = productService.findById(productId);
        Comment theComment = null;
        if (theProducts != null)
        {
            int userId = commentDTO.getUserId();
            Users theUsers = usersService.findbyId(userId);
            if(theUsers != null)
            {
                theComment = new Comment();
                theComment.setId(0);
                theComment.setName(commentDTO.getName());
                theComment.setAvatar(commentDTO.getAvatar());
                theComment.setContent(commentDTO.getContent());
                theComment.setProducts(theProducts);
                theComment.setUsers(theUsers);
                commentService.save(theComment);
            }else
                throw new NotFoundException("Not found User Id: "+userId);
        }else
            throw new NotFoundException("Not found Product Id: "+productId);
        return theComment;
    }
}
