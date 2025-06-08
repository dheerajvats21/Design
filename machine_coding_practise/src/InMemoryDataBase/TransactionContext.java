package InMemoryDataBase;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionContext {
    // would have null for keys removed
    public Map<String, String> overrides = new ConcurrentHashMap<>();

    public Map<String, Integer> countDelta = new ConcurrentHashMap<>();

    void put(String key, String newValue) {
        String oldValue = overrides.put(key, newValue);
        if (oldValue != null) countDelta.put(oldValue, countDelta.getOrDefault(oldValue, 0) - 1);
        if (newValue != null) countDelta.put(newValue, countDelta.getOrDefault(newValue, 0) + 1);
    }

    void delete(String key) {
        put(key, null); // Mark as deleted
    }

    public void merge(TransactionContext t) {
        for (Map.Entry<String, String> entry : t.overrides.entrySet()) {
            String key = entry.getKey();
            String val = entry.getValue();
            if (val == null) delete(key);
            else put(key, val);
        }

        for (Map.Entry<String, Integer> entry : t.countDelta.entrySet()) {
            String key = entry.getKey();
            Integer val = entry.getValue();
            countDelta.compute(key, (k, existing) -> {
                if (existing == null) return val;
                return existing + val;
            });
        }
    }
}
