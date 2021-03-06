package org.dendl.exercise.model;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;

import static org.junit.Assert.*;

public class AccountRepositoryImplTest {

    private Repository<Account> repository = new AccountRepositoryImpl();

    @Test(expected = AccountNotFoundException.class)
    public void getNonExistingItem() throws AccountNotFoundException {
        repository.get(0);
    }

    @Test
    public void getExistingItem() throws AccountNotFoundException, AccountIncorrectAmountValueException {
        Account account = new Account("owner#1", BigDecimal.valueOf(500));
        repository.insertOrUpdate(account);
        Account resultAccount = repository.get(account.getId());

        assertEquals(account, resultAccount);
    }

    @Test
    public void pairUpdate() throws AccountNotFoundException, AccountInsufficientBalanceException, AccountIncorrectAmountValueException {
        Account account1 = new Account("owner#1", BigDecimal.valueOf(500));
        Account account2 = new Account("owner#2", BigDecimal.valueOf(1500));
        repository.insertOrUpdate(account1);
        repository.insertOrUpdate(account2);

        long transferAmount = 500;
        repository.mutualUpdate(account2.getId(), account1.getId(), (sender, recipient) -> {
            sender.withdraw(BigDecimal.valueOf(transferAmount));
            recipient.deposit(BigDecimal.valueOf(transferAmount));
        });

        assertEquals(BigDecimal.valueOf(1000), account1.getCurrentBalance());
        assertEquals(BigDecimal.valueOf(1000), account2.getCurrentBalance());
    }

    @Test(expected = AccountNotFoundException.class)
    public void pairUpdateNonExistingItem() throws AccountNotFoundException, AccountInsufficientBalanceException, AccountIncorrectAmountValueException {
        repository.mutualUpdate(0, -1, null);
    }

    @Test
    public void listAll() throws AccountIncorrectAmountValueException {
        Account account1 = new Account("owner#1", BigDecimal.valueOf(500));
        Account account2 = new Account("owner#2", BigDecimal.valueOf(1500));
        Account account3 = new Account("owner#3", BigDecimal.valueOf(2500));
        repository.insertOrUpdate(account1);
        repository.insertOrUpdate(account2);
        repository.insertOrUpdate(account3);

        Collection<Account> accounts = repository.listAll();

        assertTrue(accounts.contains(account1));
        assertTrue(accounts.contains(account2));
        assertTrue(accounts.contains(account3));
    }
}