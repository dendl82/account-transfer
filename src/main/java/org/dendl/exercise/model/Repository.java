package org.dendl.exercise.model;

import java.util.Collection;

public interface Repository<T> {
    T get(int id) throws AccountNotFoundException;
    T insertOrUpdate(T entity);
    void mutualUpdate(int entityId1, int entityId2, AccountTransferAction<T, T> transferAction)
            throws AccountNotFoundException, AccountInsufficientBalanceException, AccountIncorrectAmountValueException;
    Collection<Account> listAll();
}
