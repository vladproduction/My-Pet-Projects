package com.app.vb.blogapp.controller;

import com.app.vb.blogapp.model.Account;
import com.app.vb.blogapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegisterController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        Account account = new Account();
        model.addAttribute("account", account);

        return "register";
    }

    //accepting new register:
    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute Account account){
        accountService.save(account);
        return "redirect:/"; //redirecting to the home page after
     }


}
