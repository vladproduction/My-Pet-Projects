package com.vladproduction.transactionsapp.controller;

import com.vladproduction.transactionsapp.service.AccountService;
import com.vladproduction.transactionsapp.transfer.TransferModel;
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
@RequestMapping("/api")
public class RestApiController {

    @Autowired
    @Qualifier("declarativeBean")
    private AccountService declarativeAccountService;

    @Autowired
    @Qualifier("platformTransactionManagerBean")
    private AccountService platformTMAccountService;

    @Autowired
    @Qualifier("transactionTemplateBean")
    private AccountService templateAccountService;

    ////////////// declarative //////////////
    @PostMapping("/declarative/transfer")
    public ResponseEntity<Void> decTransfer(@RequestBody TransferModel transferModel){
        declarativeAccountService.transfer(transferModel.getFrom(), transferModel.getTo(), transferModel.getAmount());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    ////////////// platform transaction manager //////////////
    @PostMapping("/platform/transfer")
    public ResponseEntity<Void> platTransfer(@RequestBody TransferModel transferModel){
        platformTMAccountService.transfer(transferModel.getFrom(), transferModel.getTo(), transferModel.getAmount());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    ////////////// spring template transaction management //////////////
    @PostMapping("/template/transfer")
    public ResponseEntity<Void> tempTransfer(@RequestBody TransferModel transferModel){
        templateAccountService.transfer(transferModel.getFrom(), transferModel.getTo(), transferModel.getAmount());
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
