package org.dendl.excercise.model;

import org.dendl.excercise.dao.Account;
import org.dendl.excercise.dao.AccountRepositoryImpl;
import org.dendl.excercise.dao.EntityNotFoundException;
import org.dendl.excercise.dao.Repository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

public class BankServiceImplTest {

    private BankService bankService;
    private Repository<Account> accountRepository;

    @Before
    public void setUp() {
        accountRepository = Mockito.mock(AccountRepositoryImpl.class);
        bankService = new BankServiceImpl(accountRepository);
    }

    @Test
    public void createAccount() {
        Account account1 = new Account("owner#1", BigDecimal.valueOf(500));
        Mockito.when(accountRepository.insertOrUpdate(Mockito.any(Account.class))).thenReturn(account1);
        Account resultAccount = bankService.createAccount("owner#1", BigDecimal.valueOf(500));
        Mockito.verify(accountRepository, Mockito.atLeastOnce()).insertOrUpdate(Mockito.any(Account.class));

        assertEquals(account1, resultAccount);
    }

    @Test
    public void findExistingAccountById() throws BankServiceException, EntityNotFoundException {
        Account account1 = new Account("owner#1", BigDecimal.valueOf(500));
        Mockito.when(accountRepository.get(1)).thenReturn(account1);
        Account resultAccount = bankService.findAccountById(1);
        Mockito.verify(accountRepository, Mockito.atLeastOnce()).get(Mockito.eq(1));

        assertEquals(account1, resultAccount);
    }

    @Test(expected = BankServiceException.class)
    public void findNonExistingAccountById() throws BankServiceException, EntityNotFoundException {
        Mockito.when(accountRepository.get(5)).thenThrow(EntityNotFoundException.class);
        bankService.findAccountById(5);
    }

    @Test
    public void allAccountsNonEmptyRepository() {
        Account account1 = new Account("owner#1", BigDecimal.valueOf(500));
        Account account2 = new Account("owner#2", BigDecimal.valueOf(1500));
        Account account3 = new Account("owner#3", BigDecimal.valueOf(2500));
        Mockito.when(accountRepository.listAll()).thenReturn(Arrays.asList(account1, account2, account3));
        Collection<Account> resultAccounts = bankService.allAccounts();
        Mockito.verify(accountRepository, Mockito.atLeastOnce()).listAll();

        assertNotNull(resultAccounts);
        assertFalse(resultAccounts.isEmpty());
        assertEquals(3, resultAccounts.size());
        assertTrue(resultAccounts.contains(account1));
        assertTrue(resultAccounts.contains(account2));
        assertTrue(resultAccounts.contains(account3));
    }

    @Test
    public void transfer() {
        
    }
}