package org.dendl.excercise.model;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public final class Account {
    private static final AtomicInteger ID_COUNTER = new AtomicInteger();

    private int id;
    private String ownerName;
    private BigDecimal currentBalance;

    public Account(String ownerName, BigDecimal initBalance) {
        this.id = ID_COUNTER.incrementAndGet();
        this.ownerName = ownerName;
        this.currentBalance = initBalance;
    }

    public Account() {
        this.id = ID_COUNTER.incrementAndGet();
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

    public void withdraw(BigDecimal amount) {
        this.currentBalance = this.currentBalance.subtract(amount);
    }

    public void deposit(BigDecimal amount) {
        this.currentBalance = this.currentBalance.add(amount);
    }
}
