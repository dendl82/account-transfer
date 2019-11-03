package org.dendl.exercise.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AccountTest {

    @Test
    public void testEquals() throws AccountIncorrectAmountValueException {
        Account acc1 = new Account("user1", BigDecimal.valueOf(2000));
        Account acc2 = new Account("user1", BigDecimal.valueOf(3000));
        Object acc3 = new Object();

        assertNotEquals(acc1, acc2);
        assertNotEquals(acc1, acc3);
        assertNotEquals(acc2, acc3);
    }

    @Test
    public void testHashCode() throws AccountIncorrectAmountValueException {
        Account acc1 = new Account("user1", BigDecimal.valueOf(2000));
        assertEquals(acc1.getId(), acc1.hashCode());
    }

    @Test
    public void withdraw() throws AccountInsufficientBalanceException, AccountIncorrectAmountValueException {
        Account acc1 = new Account("user1", BigDecimal.valueOf(2000));
        acc1.withdraw(BigDecimal.valueOf(500));
        assertEquals(BigDecimal.valueOf(1500), acc1.getCurrentBalance());

        acc1.withdraw(BigDecimal.valueOf(855));
        assertEquals(BigDecimal.valueOf(645), acc1.getCurrentBalance());
    }

    @Test(expected = AccountInsufficientBalanceException.class)
    public void withdrawWithExceedingAmount() throws AccountInsufficientBalanceException, AccountIncorrectAmountValueException {
        Account acc1 = new Account("user1", BigDecimal.valueOf(2000));
        acc1.withdraw(BigDecimal.valueOf(2500));
    }

    @Test
    public void deposit() throws AccountIncorrectAmountValueException {
        Account acc1 = new Account("user1", BigDecimal.valueOf(123));
        acc1.deposit(BigDecimal.valueOf(456));
        assertEquals(BigDecimal.valueOf(579), acc1.getCurrentBalance());

        acc1.deposit(BigDecimal.valueOf(221));
        assertEquals(BigDecimal.valueOf(800), acc1.getCurrentBalance());
    }

    @Test(expected = AccountIncorrectAmountValueException.class)
    public void withdrawWithNullAmountValue()
            throws AccountInsufficientBalanceException, AccountIncorrectAmountValueException {
        Account acc1 = new Account("user1", BigDecimal.valueOf(123));
        acc1.withdraw(null);
    }

    @Test(expected = AccountIncorrectAmountValueException.class)
    public void withdrawWithBelow0AmountValue()
            throws AccountInsufficientBalanceException, AccountIncorrectAmountValueException {
        Account acc1 = new Account("user1", BigDecimal.valueOf(123));
        acc1.withdraw(BigDecimal.valueOf(-1));
    }

    @Test(expected = AccountIncorrectAmountValueException.class)
    public void depositWithNullAmountValue()
            throws AccountIncorrectAmountValueException {
        Account acc1 = new Account("user1", BigDecimal.valueOf(123));
        acc1.deposit(null);
    }

    @Test(expected = AccountIncorrectAmountValueException.class)
    public void depositWithBelow0AmountValue()
            throws AccountIncorrectAmountValueException {
        Account acc1 = new Account("user1", BigDecimal.valueOf(123));
        acc1.deposit(BigDecimal.valueOf(-1));
    }

    @Test(expected = AccountIncorrectAmountValueException.class)
    public void initializeNewAccountWithNullAmountValue() throws AccountIncorrectAmountValueException {
        new Account("user1", null);
    }

    @Test(expected = AccountIncorrectAmountValueException.class)
    public void initializeNewAccountWithBelow0AmountValue() throws AccountIncorrectAmountValueException {
        new Account("user1", BigDecimal.valueOf(-1));
    }
}