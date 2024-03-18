package com.vladproduction.transactionsapp.transactional_services;

import com.vladproduction.transactionsapp.model.Account;

/**
 * Created by vladproduction on 17-Mar-24
 */

public interface WithdrawService {

    void withdraw(Account from, double amount);

}
