package org.dendl.exercise.model;

@FunctionalInterface
public interface AccountTransferAction<T, U> {
    void make(T t, U u) throws AccountInsufficientBalanceException, AccountIncorrectAmountValueException;
}
