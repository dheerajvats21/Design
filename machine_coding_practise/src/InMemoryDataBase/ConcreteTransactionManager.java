package InMemoryDataBase;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Stack;

public class ConcreteTransactionManager implements TransactionManager {
    Deque<TransactionContext> contexts = new ArrayDeque<>();

    public void begin() {
        contexts.addLast(new TransactionContext());
    }

    public void rollback() {
        if (inTransaction()) {
            System.out.println("rolling back size = " + contexts.size());
            contexts.removeLast();
        }
    }

    public void commit(DataBase database) {
        System.out.println("commiting size = " + contexts.size());
        if (contexts.size() < 2) {
            TransactionContext last = contexts.removeLast();
            database.merge(last);
        } else {
            TransactionContext last = contexts.removeLast();
            contexts.peekLast().merge(last);
        }
    }

    @Override
    public boolean inTransaction() {
        return !contexts.isEmpty() ;
    }

    public TransactionContext giveCurrentContext() {
        return contexts.getLast();
    }

    @Override
    public void put(String key, String value) {
        if (inTransaction()) giveCurrentContext().put(key, value);
    }

    @Override
    public String get(String key) {
        System.out.println("szie of context = " + contexts.size());
        Iterator<TransactionContext> it = contexts.descendingIterator();
        while (it.hasNext()) {
            TransactionContext contextVal = it.next();
            if (contextVal.overrides.containsKey(key)) {
                System.out.println("found in context");
                return contextVal.overrides.get(key);
            }
        }
        return null;
    }

    @Override
    public void delete(String key) {
        if (inTransaction()) giveCurrentContext().delete(key);
    }

    @Override
    public boolean exists(String key) {
        String val = get(key);
        return val != null;
    }

    @Override
    public int count(String value) {
        Iterator<TransactionContext> it = contexts.descendingIterator();
        int countValue = 0;
        while (it.hasNext()) {
            TransactionContext contextVal = it.next();
            if (contextVal.countDelta.containsKey(value)) {
                countValue += contextVal.countDelta.get(value);
            }
        }
        return countValue;
    }

}
