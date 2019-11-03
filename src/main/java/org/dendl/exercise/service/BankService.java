package org.dendl.exercise.service;

import org.dendl.exercise.model.Account;

import java.math.BigDecimal;
import java.util.Collection;

public interface BankService {
    Account createAccount(String owner, BigDecimal initialBalance) throws BankServiceException;
    Account findAccountById(int id) throws BankServiceException;
    Collection<Account> allAccounts();
    void transfer(BigDecimal amount, int fromAccountId, int toAccountId) throws BankServiceException;
}
