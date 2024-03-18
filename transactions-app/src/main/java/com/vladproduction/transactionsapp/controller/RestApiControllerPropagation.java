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
public class RestApiControllerPropagation {

    @Autowired
    @Qualifier("accountServiceImplPropagation")
    private AccountService propagationAccountService;


    ////////////// propagation //////////////
    @PostMapping("/propagation/transfer")
    public ResponseEntity<Void> tempTransfer(@RequestBody TransferModel transferModel){
        propagationAccountService.transfer(transferModel.getFrom(), transferModel.getTo(), transferModel.getAmount());
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
