package org.dendl.excercise.model;

import org.dendl.excercise.dao.Account;

import java.math.BigDecimal;
import java.util.Collection;

public interface BankService {
    Account createAccount(String owner, BigDecimal initialBalance);
    Account findAccountById(int id) throws BankServiceException;
    Collection<Account> allAccounts();
    void transfer(BigDecimal amount, int fromAccountId, int toAccountId) throws BankServiceException;
}
