package com.vladproduction.transactionsapp.controllers;

import com.vladproduction.transactionsapp.dto.TransferModel;
import com.vladproduction.transactionsapp.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vladproduction on 28-Mar-24
 */

@RestController
@RequestMapping("/api/platform")
public class PlatformTransactionManagerController {

    @Autowired
    @Qualifier("platformTransactionManagerBean")
    private AccountService platformTMAS;

    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestBody TransferModel transferModel){
        platformTMAS.transfer(transferModel.getFrom(), transferModel.getTo(), transferModel.getAmount());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
