package org.dendl.exercise.service;

import org.dendl.exercise.model.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BankServiceImplTest {

    private BankService bankService;
    private Repository<Account> accountRepository;

    @Before
    public void setUp() {
        accountRepository = mock(AccountRepositoryImpl.class);
        bankService = new BankServiceImpl(accountRepository);
    }

    @Test
    public void createAccount() throws BankServiceException {
        Account resultAccount = bankService.createAccount("owner#1", BigDecimal.valueOf(500));
        verify(accountRepository, atLeastOnce()).insertOrUpdate(eq(resultAccount));
    }

    @Test
    public void findExistingAccountById()
            throws BankServiceException, AccountNotFoundException, AccountIncorrectAmountValueException {
        Account account1 = new Account("owner#1", BigDecimal.valueOf(500));
        when(accountRepository.get(1)).thenReturn(account1);
        Account resultAccount = bankService.findAccountById(1);
        verify(accountRepository, atLeastOnce()).get(eq(1));

        assertEquals(account1, resultAccount);
    }

    @Test(expected = BankServiceException.class)
    public void findNonExistingAccountById() throws BankServiceException, AccountNotFoundException {
        when(accountRepository.get(5)).thenThrow(AccountNotFoundException.class);
        bankService.findAccountById(5);
    }

    @Test
    public void allAccountsNonEmptyRepository() throws AccountIncorrectAmountValueException {
        Account account1 = new Account("owner#1", BigDecimal.valueOf(500));
        Account account2 = new Account("owner#2", BigDecimal.valueOf(1500));
        Account account3 = new Account("owner#3", BigDecimal.valueOf(2500));
        when(accountRepository.listAll()).thenReturn(Arrays.asList(account1, account2, account3));
        Collection<Account> resultAccounts = bankService.allAccounts();
        verify(accountRepository, atLeastOnce()).listAll();

        assertNotNull(resultAccounts);
        assertFalse(resultAccounts.isEmpty());
        assertEquals(3, resultAccounts.size());
        assertTrue(resultAccounts.contains(account1));
        assertTrue(resultAccounts.contains(account2));
        assertTrue(resultAccounts.contains(account3));
    }

    @Test
    public void transfer() throws BankServiceException, AccountNotFoundException, AccountInsufficientBalanceException, AccountIncorrectAmountValueException {
        bankService.transfer(BigDecimal.valueOf(500), 1, 2);
        verify(accountRepository, atLeastOnce())
                .mutualUpdate(eq(1), eq(2), any());
    }

    @Test(expected = BankServiceException.class)
    public void transferThrowsException() throws BankServiceException, AccountNotFoundException, AccountInsufficientBalanceException, AccountIncorrectAmountValueException {
        doThrow(AccountNotFoundException.class)
                .when(accountRepository)
                .mutualUpdate(eq(1), eq(2), any());
        bankService.transfer(BigDecimal.valueOf(500), 1, 2);
    }
}