package Logger;

public interface LoggerRateLimiter {
    boolean addLog(String key, String log);
    void allowKey(String key);
}

