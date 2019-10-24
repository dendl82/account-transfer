package org.dendl.exercise.dao;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.BiConsumer;

public class AccountRepositoryImpl implements Repository<Account> {
    private static final HashMap<Integer, Account> STORAGE = new HashMap<>();

    @Override
    public Account get(int id) throws EntityNotFoundException {
        if (!STORAGE.containsKey(id)) {
            throw new EntityNotFoundException("Couldn't find an Account entity with id=" + id);
        }
        return STORAGE.get(id);
    }

    @Override
    public Account insertOrUpdate(Account entity) {
        return STORAGE.put(entity.getId(), entity);
    }

    @Override
    public void mutualUpdate(int entityId1, int entityId2, BiConsumer<Account, Account> actionTaken) throws EntityNotFoundException {
        Account entity1 = get(entityId1);
        Account entity2 = get(entityId2);
        Object lockObj1 = entity1.getId() < entity2.getId() ? entity1 : entity2;
        Object lockObj2 = entity2.getId() < entity1.getId() ? entity1 : entity2;

        synchronized (lockObj1) {
            synchronized (lockObj2) {
                actionTaken.accept(entity1, entity2);
            }
        }
    }

    @Override
    public Collection<Account> listAll() {
        return Collections.unmodifiableCollection(STORAGE.values());
    }
}
