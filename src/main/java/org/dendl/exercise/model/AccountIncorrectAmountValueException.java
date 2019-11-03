package org.dendl.exercise.model;

public class AccountIncorrectAmountValueException extends Exception {
    public AccountIncorrectAmountValueException(String message) {
        super(message);
    }
}
