package org.dendl.excercise.model;

public class BankServiceException extends Exception {
    public BankServiceException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
