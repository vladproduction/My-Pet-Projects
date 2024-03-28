package com.vladproduction.transactionsapp.services.propagation_example02;

import com.vladproduction.transactionsapp.dao.AccountDao;
import com.vladproduction.transactionsapp.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vladproduction on 28-Mar-24
 */

@Service
public class AddAccountDao {

    @Autowired
    private AccountDao accountDao;

//    @Transactional(propagation = Propagation.NEVER) //able to create account through controller directly
    @Transactional(propagation = Propagation.MANDATORY) //No existing transaction found for transaction marked with propagation 'mandatory'
    public void addAccount(Account account){
        accountDao.save(account);
    }

    /**if addAccount marked like this we have to include this in surround transaction,
     * so: addAccountDao.addAccount(account); in scope of method transfer() */

}
