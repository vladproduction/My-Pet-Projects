package com.vladproduction.transactionsapp.controllers;

import com.vladproduction.transactionsapp.model.Account;
import com.vladproduction.transactionsapp.services.AccountService;
import com.vladproduction.transactionsapp.dto.TransferModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vladproduction on 15-Mar-24
 */

@RestController
@RequestMapping("/api/propagation")
public class PropagationController {

    @Autowired
    @Qualifier("accountServicePropagationBean")
    private AccountService propagationAS;

    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestBody TransferModel transferModel){
        propagationAS.transfer(transferModel.getFrom(), transferModel.getTo(), transferModel.getAmount());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addAccount")
    public ResponseEntity<Void> addAccount(@RequestBody Account account){
        propagationAS.addAccount(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
