package org.dendl.excercise.model;

import org.dendl.excercise.dao.AccountRepositoryImpl;

public enum BankServiceBuilder {
    DEFAULT_BANK;

    private BankService bankService;

    BankServiceBuilder() {
        bankService = new BankServiceImpl(new AccountRepositoryImpl());
    }

    public BankService getBankService() {
        return bankService;
    }
}
