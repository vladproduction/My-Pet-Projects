package com.vladproduction.transactionsapp.services.propagation_example02;

import com.vladproduction.transactionsapp.dao.AccountDao;
import com.vladproduction.transactionsapp.model.Account;
import com.vladproduction.transactionsapp.services.AccountService;
import com.vladproduction.transactionsapp.services.propagation_example01.DepositService;
import com.vladproduction.transactionsapp.services.propagation_example01.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by vladproduction on 28-Mar-24
 */

@Service
@Transactional
@Qualifier("accountServiceBehaviourBean")
public class AccountServiceBehaviour implements AccountService {

    @Autowired
    private DepositService depositService;

    @Autowired
    private WithdrawService withdrawService;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AddAccountDao addAccountDao;

    @Override
    @Transactional(propagation = Propagation.NEVER) //does not make any actions in this class scope
    public void addAccount(Account account) {accountDao.save(account);}
    @Override
    public List<Account> getAllAccount() {return accountDao.findAll();}
    @Override
    public Optional<Account> getAccount(int id) {
        return accountDao.findById((long)id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transfer(Account from, Account to, double amount) {

        withdrawService.withdraw(from, amount);
        depositService.deposit(to, amount);
        Account account = new Account();
        account.setAccountNumber(555);
        account.setBalance(5000.0);
        account.setOwner("Bobby");
        addAccount(account); //from the same class (this class)
//        addAccountDao.addAccount(account); //from another class

    }


}
