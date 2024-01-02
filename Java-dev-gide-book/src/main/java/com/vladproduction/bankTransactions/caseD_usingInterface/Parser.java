package com.vladproduction.bankTransactions.caseD_usingInterface;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.File;
import java.util.List;

public interface Parser {

    BankTransaction lineFromFile(String line) throws JsonProcessingException;

    List<BankTransaction> loadFromFile(File file);
}
