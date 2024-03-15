package com.vladproduction.transactionsapp.dao;

import com.vladproduction.transactionsapp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vladproduction on 15-Mar-24
 */

public interface AccountDao extends JpaRepository<Account, Long> {
}
