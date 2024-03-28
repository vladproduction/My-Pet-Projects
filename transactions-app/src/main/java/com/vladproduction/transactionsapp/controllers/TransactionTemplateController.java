package com.vladproduction.transactionsapp.controllers;

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
@RequestMapping("/api/template")
public class TransactionTemplateController {


    @Autowired
    @Qualifier("transactionTemplateBean")
    private AccountService templateAccountService;

    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestBody TransferModel transferModel){
        templateAccountService.transfer(transferModel.getFrom(), transferModel.getTo(), transferModel.getAmount());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
