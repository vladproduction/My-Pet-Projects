package com.vladproduction.transactionsapp.service;

import com.vladproduction.transactionsapp.dao.AccountDao;
import com.vladproduction.transactionsapp.model.Account;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by vladproduction on 15-Mar-24
 */
/**
 * Declarative approach:
 * separate transaction logic from business logic
 * */

@Service
@Transactional
@Qualifier("declarativeBean")
public class AccountServiceDecImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public void addAccount(Account account) {
        accountDao.save(account);
    }

    @Override
    public List<Account> getAllAccount() {
        return accountDao.findAll();
    }

    @Override
    public void transfer(Account from, Account to, double amount) {
        //have to create two methods (2 steps for transaction: 1-withdraw; 2-deposit)
        withdraw(from, amount);
        deposit(to, amount);
    }

    @Override
    public Optional<Account> getAccount(int id) {
        return accountDao.findById((long) id);
    }

    @SuppressWarnings("from account: withdraw")
    private void withdraw(Account from, double amount) {
        Account accountDebit = getAccount(from.getId().intValue()).get();
        if(amount < 0){
            throw new RuntimeException("Error! Withdraw invalid for account: " + accountDebit.getAccountNumber()
                    + ". Owner name: " + accountDebit.getOwner());
        } else if (amount > accountDebit.getBalance()) {
            throw new RuntimeException("Error! Insufficient funds.\n Account:  " + accountDebit.getAccountNumber()
                    + ". Requested: " + amount + "Balance is: " + accountDebit.getBalance());
        } else {

            accountDebit.setBalance(accountDebit.getBalance() - amount);
            accountDebit.setLast_operation("Withdrawal success!\n Balance is: " + accountDebit.getBalance());
        }
    }

    @SuppressWarnings("to account: deposit")
    private void deposit(Account to, double amount) {
        Account accountCredit = getAccount(to.getId().intValue()).get();
        if(amount < 0){
            throw new RuntimeException("Error: Deposit amount is invalid." + accountCredit.getAccountNumber() + " "
            + amount);
        } else {
            accountCredit.setBalance(accountCredit.getBalance() + amount);
            accountCredit.setLast_operation("Deposit success!\n Balance is: " + accountCredit.getBalance());
        }
    }
}
