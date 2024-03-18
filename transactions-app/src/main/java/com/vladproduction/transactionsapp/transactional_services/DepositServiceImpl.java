package com.vladproduction.transactionsapp.transactional_services;

import com.vladproduction.transactionsapp.dao.AccountDao;
import com.vladproduction.transactionsapp.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.nio.ByteBuffer;
import java.util.Optional;

/**
 * Created by vladproduction on 17-Mar-24
 */
@Service
//@Transactional(propagation = Propagation.REQUIRES_NEW)
//@Transactional(propagation = Propagation.NEVER) // does not work in transactions scope
@Transactional(propagation = Propagation.MANDATORY) // always work in transactions scope

public class DepositServiceImpl implements DepositService {

    @Autowired
    private AccountDao accountDao;


    @Override
    public void deposit(Account to, double amount) {
        Account accountCredit = getAccount(to.getId().intValue()).get();
        if (amount < 0){
            throw new RuntimeException(
                    "Error: Deposit amount is invalid." + accountCredit.getAccountNumber() + " " + amount);
        } else {
            accountCredit.setBalance(accountCredit.getBalance() + amount);
            accountCredit.setLast_operation("Credited".toUpperCase());
        }
    }

    private Optional<Account> getAccount(int id) {
        return accountDao.findById((long)id);
    }
}
