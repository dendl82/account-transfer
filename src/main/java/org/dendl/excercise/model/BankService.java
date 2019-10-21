package org.dendl.excercise.model;

import java.math.BigDecimal;
import java.util.List;

public interface BankService {
    Account createAccount(String owner, BigDecimal initialBalance);
    Account findById(int id);
    List<Account> allAccounts();
    boolean transfer(BigDecimal amount, int fromAccountId, int toAccountId);
}
