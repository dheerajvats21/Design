package InMemoryDataBase;

public interface DataBase {
    void put(String key, String value);
    String get(String key);
    void delete(String key);
    boolean exists(String key);
    int count(String value);
    void commit();
    void rollback ();
    void begin();
    void merge(TransactionContext c);
}
