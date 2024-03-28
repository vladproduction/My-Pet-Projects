package com.vladproduction.transactionsapp.services.propagation_example01;

import com.vladproduction.transactionsapp.dao.AccountDao;
import com.vladproduction.transactionsapp.model.Account;
import com.vladproduction.transactionsapp.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by vladproduction on 17-Mar-24
 */
@Service
@Transactional
@Qualifier("accountServicePropagationBean")
public class AccountServicePropagation implements AccountService {
    @Autowired
    private DepositService depositService;
    @Autowired
    private WithdrawService withdrawService;
    @Autowired
    private AccountDao accountDao;

    @Override
    public void addAccount(Account account) {accountDao.save(account);}
    @Override
    public List<Account> getAllAccount() {return accountDao.findAll();}
    @Override
    public Optional<Account> getAccount(int id) {
        return accountDao.findById((long)id);
    }
    @Override
    public void transfer(Account from, Account to, double amount) {
        withdrawService.withdraw(from, amount);
        depositService.deposit(to, amount);
    }

}
