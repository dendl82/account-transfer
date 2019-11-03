package org.dendl.exercise.model;

public class AccountInsufficientBalanceException extends Exception {
    public AccountInsufficientBalanceException(String message) {
        super(message);
    }
}
