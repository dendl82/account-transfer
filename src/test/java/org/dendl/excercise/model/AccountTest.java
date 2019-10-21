package org.dendl.excercise.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void testEquals() {
        Account acc1 = new Account("user1", BigDecimal.valueOf(2000));
        Account acc2 = new Account("user1", BigDecimal.valueOf(3000));
        Object acc3 = new Object();

        assertNotEquals(acc1, acc2);
        assertNotEquals(acc1, acc3);
        assertNotEquals(acc2, acc3);
    }

    @Test
    public void testHashCode() {
        Account acc1 = new Account("user1", BigDecimal.valueOf(2000));
        assertEquals(acc1.getId(), acc1.hashCode());
    }

    @Test
    public void withdraw() {
        Account acc1 = new Account("user1", BigDecimal.valueOf(2000));
        acc1.withdraw(BigDecimal.valueOf(500));
        assertEquals(BigDecimal.valueOf(1500), acc1.getCurrentBalance());

        acc1.withdraw(BigDecimal.valueOf(855));
        assertEquals(BigDecimal.valueOf(645), acc1.getCurrentBalance());
    }

    @Test
    public void deposit() {
        Account acc1 = new Account("user1", BigDecimal.valueOf(123));
        acc1.deposit(BigDecimal.valueOf(456));
        assertEquals(BigDecimal.valueOf(579), acc1.getCurrentBalance());

        acc1.deposit(BigDecimal.valueOf(221));
        assertEquals(BigDecimal.valueOf(800), acc1.getCurrentBalance());
    }
}