package com.app.vb.blogapp.service;

import com.app.vb.blogapp.model.Account;
import com.app.vb.blogapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account save(Account account){
        //encoding password first
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        return accountRepository.save(account);
    }

    public Optional<Account> findByEmail(String email){
        return accountRepository.findOneByEmail(email);
    }

}
