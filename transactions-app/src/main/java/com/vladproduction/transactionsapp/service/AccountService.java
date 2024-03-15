package com.vladproduction.transactionsapp.service;

import com.vladproduction.transactionsapp.model.Account;

import java.util.List;
import java.util.Optional;

/**
 * Created by vladproduction on 15-Mar-24
 */

public interface AccountService {

    void addAccount(Account account);
    List<Account> getAllAccount();
    void transfer(Account from, Account to, double amount);
    Optional<Account> getAccount(int id);


}
