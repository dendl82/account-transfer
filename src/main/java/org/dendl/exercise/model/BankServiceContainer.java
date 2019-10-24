package org.dendl.exercise.model;

import org.dendl.exercise.dao.AccountRepositoryImpl;

public enum BankServiceContainer {
    DEFAULT_BANK;

    private BankService bankService;

    BankServiceContainer() {
        bankService = new BankServiceImpl(new AccountRepositoryImpl());
    }

    public BankService getBankService() {
        return bankService;
    }
}
