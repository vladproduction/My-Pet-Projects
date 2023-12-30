package com.vladproduction.bankTransactions.DusingInterface;

import java.io.File;
import java.util.List;

public interface Parser {

    BankTransaction lineFromFile(String line);

    List<BankTransaction> loadFromFile(File file);
}
