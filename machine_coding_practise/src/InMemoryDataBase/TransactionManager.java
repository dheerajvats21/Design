package InMemoryDataBase;

public interface TransactionManager {
    public void begin();

    public void rollback();

    public void commit(DataBase dataBase);

    public boolean inTransaction();

    public void put(String key, String value);
    String get(String key);
    void delete(String key);
    boolean exists(String key);
    int count(String value);

}
