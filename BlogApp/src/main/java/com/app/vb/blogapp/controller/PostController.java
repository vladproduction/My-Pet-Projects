package com.app.vb.blogapp.controller;

import com.app.vb.blogapp.model.Account;
import com.app.vb.blogapp.model.Post;
import com.app.vb.blogapp.service.AccountService;
import com.app.vb.blogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model){
        //find post by id
        Optional<Post> optionalPost = postService.getById(id);
        //if post exist - shove it into the model
        if(optionalPost.isPresent()){
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "post";
        }else {
            return "404";
        }
    }

    //two new mappings for creating new posts by existing user
    //1)creating post by filling form
    @GetMapping("/posts/new")
    public String createNewPost(Model model){
        Optional<Account> optionalAccount = accountService.findByEmail("user@gmail.com");
        if (optionalAccount.isPresent()){
            Post post = new Post();
            post.setAccount(optionalAccount.get());
            model.addAttribute("post", post);
            return "post_new";
        } else {
            return "404";
        }
    }

    //2)posting created post
    @PostMapping("/posts/new")
    public String saveNewPost(@ModelAttribute Post post){
        postService.save(post);
        return "redirect:/posts/" + post.getId();
    }

    //endpoint to delete post
    @GetMapping("/posts/{id}/delete")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deletePost(@PathVariable Long id){
        //find post by id first
        Optional<Post> optionalPost = postService.getById(id);
        //if exist delete it
        if(optionalPost.isPresent()){
            Post post = optionalPost.get();
            postService.delete(post);
            return "redirect:/";
        }else {
            return "404";
        }
    }


    //endpoint to get post for updating
    @GetMapping("/posts/{id}/update")
    @PreAuthorize("isAuthenticated()")
    public String getPostForUpdate(@PathVariable Long id, Model model){
        //find post byId first
        Optional<Post> optionalPost = postService.getById(id);
        //if exist put it in model
        if(optionalPost.isPresent()){
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "post_update";
        }else {
            return "404";
        }
    }

    //endpoint to update post
    @PostMapping("/posts/{id}")
    @PreAuthorize("isAuthenticated()")
    public String updatePost(@PathVariable Long id, Post post, BindingResult result, Model model){
        Optional<Post> optionalPost = postService.getById(id);
        if(optionalPost.isPresent()){
            Post existingPost = optionalPost.get();
            existingPost.setTitle(post.getTitle());
            existingPost.setBody(post.getBody());
            postService.save(existingPost);
        }
        return "redirect:/posts/" + post.getId();
    }

}
