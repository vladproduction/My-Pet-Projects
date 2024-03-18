package com.vladproduction.transactionsapp.service;

import com.vladproduction.transactionsapp.model.Account;
import com.vladproduction.transactionsapp.transactional_services.DepositService;
import com.vladproduction.transactionsapp.transactional_services.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by vladproduction on 17-Mar-24
 */
@Service
@Transactional
public class AccountServiceImplPropagation implements AccountService {

    @Autowired
    private DepositService depositService;

    @Autowired
    private WithdrawService withdrawService;
    @Override
    public void addAccount(Account account) {

    }

    @Override
    public List<Account> getAllAccount() {
        return null;
    }

    @Override
    public void transfer(Account from, Account to, double amount) {
        withdrawService.withdraw(from, amount);
        depositService.deposit(to, amount);
//        String s = null;
//        System.out.println(s.hashCode());
    }

    @Override
    public Optional<Account> getAccount(int id) {
        return Optional.empty();
    }
}
