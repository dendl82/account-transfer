package org.dendl.exercise.service;

import org.dendl.exercise.model.AccountRepositoryImpl;

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
