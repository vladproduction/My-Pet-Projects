package com.vladproduction.transactionsapp.service;

import com.vladproduction.transactionsapp.dao.AccountDao;
import com.vladproduction.transactionsapp.model.Account;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.Optional;

/**
 * Created by vladproduction on 15-Mar-24
 */
/**
 * Platform transaction manager approach:
 * programmatic transaction management using directly the transactionManager
 * */
@Service
//@Transactional
@Qualifier("platformTransactionManagerBean")
public class AccountServicePlatformTMImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

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
        //need to have 2 steps as well (withdraw and deposit)
        //and we commit if all goes fine, otherwise rollback
        //transaction manager responsible for those operations

        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);

        try{
            withdraw(from, amount);
            deposit(to, amount);
            platformTransactionManager.commit(transactionStatus);
        }catch (RuntimeException e){
            platformTransactionManager.rollback(transactionStatus);
            throw e;
        }

    }

    private void withdraw(Account from, double amount) {
        Account accountDebit = getAccount(from.getId().intValue()).get();

        if (amount < 0){
            throw new RuntimeException("Error: Withdraw amount is invalid. for the Account: "
                    + accountDebit.getAccountNumber() + " Name: " + accountDebit.getOwner());
        }else if (amount > accountDebit.getBalance()){
            throw new RuntimeException(
                    "Error: Insufficient funds.\n Account: " + accountDebit.getAccountNumber() + "\n" + "Requested:"
                            + amount + "Available: " + "\n" + accountDebit.getBalance());
        }else {
            accountDebit.setBalance(accountDebit.getBalance() - amount);
            accountDebit.setLast_operation("debited".toUpperCase());
        }

    }

    private void deposit(Account to, double amount) {
        Account accountCredit = getAccount(to.getId().intValue()).get();

        if(amount < 0) throw new RuntimeException("Error: Deposit amount is invalid." + accountCredit.getAccountNumber() + " " + amount);
        else {
            accountCredit.setBalance(accountCredit.getBalance() + amount);
            accountCredit.setLast_operation("credited".toUpperCase());
        }
    }

    @Override
    public Optional<Account> getAccount(int id) {
        return accountDao.findById((long)id);
    }
}
