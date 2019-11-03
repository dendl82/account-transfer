package org.dendl.exercise.model;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public final class Account {
    private static final AtomicInteger ID_COUNTER = new AtomicInteger();

    private int id;
    private String ownerName;
    private BigDecimal currentBalance;

    public Account(String ownerName, BigDecimal initBalance) throws AccountIncorrectAmountValueException {
        checkAmountValue(initBalance);
        this.id = ID_COUNTER.incrementAndGet();
        this.ownerName = ownerName;
        this.currentBalance = initBalance;
    }

    public int getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return id == account.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", owner name='" + ownerName + '\'' +
                ", current balance=" + currentBalance +
                '}';
    }

    public void withdraw(BigDecimal amount)
            throws AccountInsufficientBalanceException, AccountIncorrectAmountValueException {
        checkAmountValue(amount);
        if (amount.compareTo(this.currentBalance) > 0) {
            throw new AccountInsufficientBalanceException("Not enough funds in the account to complete transfer.");
        }
        this.currentBalance = this.currentBalance.subtract(amount);
    }

    public void deposit(BigDecimal amount) throws AccountIncorrectAmountValueException {
        checkAmountValue(amount);
        this.currentBalance = this.currentBalance.add(amount);
    }

    private void checkAmountValue(BigDecimal amount) throws AccountIncorrectAmountValueException {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new AccountIncorrectAmountValueException("Amount value mustn't be null, and must be greater or equals zero.");
        }
    }
}
