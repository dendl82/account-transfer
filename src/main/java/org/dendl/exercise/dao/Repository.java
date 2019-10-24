package org.dendl.exercise.dao;

import java.util.Collection;
import java.util.function.BiConsumer;

public interface Repository<T> {
    T get(int id) throws EntityNotFoundException;
    T insertOrUpdate(T entity);
    void mutualUpdate(int entityId1, int entityId2, BiConsumer<T, T> actionTaken) throws EntityNotFoundException;
    Collection<Account> listAll();
}
