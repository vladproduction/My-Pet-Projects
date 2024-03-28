package com.vladproduction.transactionsapp.controllers;

import com.vladproduction.transactionsapp.dao.AccountDao;
import com.vladproduction.transactionsapp.model.Account;
import com.vladproduction.transactionsapp.services.propagation_example02.AccountServiceBehaviour;
import com.vladproduction.transactionsapp.services.propagation_example02.AddAccountDao;
import com.vladproduction.transactionsapp.dto.TransferModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by vladproduction on 28-Mar-24
 */

@RestController
@RequestMapping("/api/behaviour")
public class BehaviourController {

    @Autowired
    @Qualifier("accountServiceBehaviourBean")
    private AccountServiceBehaviour behaviourAS;

    @Autowired
    private AddAccountDao addAccountDao;


    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestBody TransferModel transferModel){
        behaviourAS.transfer(transferModel.getFrom(), transferModel.getTo(), transferModel.getAmount());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**to make this done we have two approach:
     * 1)include in existing transaction (talking about transfer method), propagation-MANDATORY;
     * 2)call directly from another class (AddAccountDao), but propagation should be as NEVER */
    @PostMapping("/addAccount")
    public void addAccount(@RequestBody Account account) {
        addAccountDao.addAccount(account);
    }

}
