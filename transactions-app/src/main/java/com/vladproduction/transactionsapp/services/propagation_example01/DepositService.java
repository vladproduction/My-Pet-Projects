package com.vladproduction.transactionsapp.services.propagation_example01;

import com.vladproduction.transactionsapp.model.Account;

/**
 * Created by vladproduction on 17-Mar-24
 */

public interface DepositService {

     void deposit(Account to, double amount);

}
