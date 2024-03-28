package com.vladproduction.transactionsapp.services.propagation_example01;

import com.vladproduction.transactionsapp.dao.AccountDao;
import com.vladproduction.transactionsapp.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by vladproduction on 17-Mar-24
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
//@Transactional(propagation = Propagation.REQUIRES_NEW)
public class WithdrawServiceImpl implements WithdrawService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public void withdraw(Account from, double amount) {
        Account accountDebit = getAccount(from.getId().intValue()).get();
        if(amount < 0){
            throw new RuntimeException("Error: Withdraw amount is invalid. for the Account: "
                    + accountDebit.getAccountNumber() + " Name: " + accountDebit.getOwner());
        } else if (amount > accountDebit.getBalance()) {
            throw new RuntimeException(
                    "Error: Insufficient funds.\n Account: " + accountDebit.getAccountNumber() + "\n" + "Requested:"
                            + amount + "Available: " + "\n" + accountDebit.getBalance());
        } else {
            accountDebit.setBalance(accountDebit.getBalance() - amount);
            accountDebit.setLast_operation("Withdrawal success!\n Balance is: " + accountDebit.getBalance() +
                    "\n(sent amount: " + amount + ")");
        }
    }

    private Optional<Account> getAccount(int id) {
        return accountDao.findById((long)id);
    }
}
