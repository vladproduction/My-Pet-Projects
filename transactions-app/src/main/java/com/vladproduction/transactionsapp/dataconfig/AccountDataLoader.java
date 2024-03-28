package com.vladproduction.transactionsapp.dataconfig;

import com.vladproduction.transactionsapp.dao.AccountDao;
import com.vladproduction.transactionsapp.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by vladproduction on 15-Mar-24
 */

@Component
public class AccountDataLoader implements CommandLineRunner {

    @Autowired
    private AccountDao accountDao;

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        if(accountDao.count() == 0){
            Account account1 = new Account(111, 2000, "John Dow");
            Account account2 = new Account(222, 1000, "Jane Dow");
            Account account3 = new Account(333, 700, "Jack Dow");
            accountDao.save(account1);
            accountDao.save(account2);
            accountDao.save(account3);
        }
    }
}
