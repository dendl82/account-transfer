package org.dendl.exercise.model;

import org.dendl.exercise.dao.Account;
import org.dendl.exercise.dao.EntityNotFoundException;
import org.dendl.exercise.dao.Repository;

import java.math.BigDecimal;
import java.util.Collection;

public class BankServiceImpl implements BankService {
    private Repository<Account> repository;

    BankServiceImpl(Repository<Account> repository) {
        this.repository = repository;
    }

    @Override
    public Account createAccount(String owner, BigDecimal initialBalance) {
        Account account = new Account(owner, initialBalance);
        repository.insertOrUpdate(account);

        return account;
    }

    @Override
    public Account findAccountById(int id) throws BankServiceException {
        try {
            return repository.get(id);
        } catch (EntityNotFoundException e) {
            throw new BankServiceException("Such an Account doesn't exist.", e);
        }
    }

    @Override
    public Collection<Account> allAccounts() {
        return repository.listAll();
    }

    @Override
    public void transfer(BigDecimal amount, int fromAccountId, int toAccountId) throws BankServiceException {
        try {
            repository.mutualUpdate(fromAccountId, toAccountId, (accountFrom, accountTo) -> {
                accountFrom.withdraw(amount);
                accountTo.deposit(amount);
            });
        } catch (EntityNotFoundException e) {
            throw new BankServiceException("Transfer hasn't finished with success.", e);
        }
    }
}
