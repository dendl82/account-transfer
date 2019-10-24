package org.dendl.exercise.model;

public class BankServiceException extends Exception {
    public BankServiceException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
