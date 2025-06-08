package InMemoryDataBase;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryDatabase implements DataBase {
    Map<String, String> keyValues = new ConcurrentHashMap<>();

    Map<String, AtomicInteger> countMap = new ConcurrentHashMap<>();

    TransactionManager manager;


    public InMemoryDatabase(TransactionManager manager) {
        this.manager = manager;
    }

    @Override
    public void put(String key, String value) {
        if (manager.inTransaction()) {
            manager.put(key, value);
            return;
        }

        keyValues.compute(key, (k, oldValue) -> {
            countMap.compute(value, (valueKey, countForValue) -> {
                if (countForValue == null) return new AtomicInteger(1);
                countForValue.incrementAndGet();
                return countForValue;
            });
            System.out.println("aa" + countMap.size());
            if (oldValue != null) {
                countMap.compute(oldValue, (oldValueKey, countOfOldValue) -> {
                    System.out.println("aass");

                    if (countOfOldValue != null) {
                        if (countOfOldValue.decrementAndGet() == 0) return null;
                        return countOfOldValue;
                    }
                    return null;
                });
            }


            return value;
        });
    }

    @Override
    public String get(String key) {
        String val;
        if (manager.inTransaction()) {
            val = manager.get(key);
            if (val != null) return val;
        }
        return keyValues.get(key);
    }

    @Override
    public void delete(String key) {
        if (manager.inTransaction()) {
            manager.delete(key);
            return;
        }

        keyValues.compute(key, (k, value) -> {
            if (value != null) {
                countMap.compute(value, (oldValueKey, oldValueCount) -> {
                   if (oldValueCount == null) return null;
                   if (oldValueCount.decrementAndGet() == 0) return null;
                   return oldValueCount;
                });
            }

            return null;
        });
    }

    @Override
    public boolean exists(String key) {
        boolean exits = false;
        if (manager.inTransaction()) {
            exits = manager.exists(key);
        }
        return keyValues.containsKey(key) || exits;
    }

    @Override
    public int count(String value) {
        int count = 0;
        if (manager.inTransaction()) {
            count = manager.count(value);
        }
        AtomicInteger val = countMap.get(value);
        int realDBValue = val == null ? 0 : val.get();
        return realDBValue + count;
    }

    public void begin() {
        manager.begin();
    }

    @Override
    public void merge(TransactionContext t) {
        for (Map.Entry<String, String> entry : t.overrides.entrySet()) {
            String key = entry.getKey();
            String val = entry.getValue();
            if (val == null) keyValues.remove(key);
            else {
                keyValues.put(key, val);
            }
        }

        for (Map.Entry<String, Integer> entry : t.countDelta.entrySet()) {
            String key = entry.getKey();
            Integer val = entry.getValue();
            countMap.compute(key, (keyValue, currentValue) -> {
               if (currentValue == null) new AtomicInteger(val);
               currentValue.addAndGet(val);
               return currentValue;
            });
        }
    }

    public void rollback() {
        manager.rollback();
    }

    public void commit() {
        manager.commit(this);
    }




}
