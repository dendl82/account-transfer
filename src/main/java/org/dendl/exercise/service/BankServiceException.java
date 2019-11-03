package org.dendl.exercise.service;

public class BankServiceException extends Exception {
    public BankServiceException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
