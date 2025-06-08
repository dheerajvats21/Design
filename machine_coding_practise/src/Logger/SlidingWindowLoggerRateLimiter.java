package Logger;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowLoggerRateLimiter implements LoggerRateLimiter {
    Map<String, Queue<Long>> members ;
    int maxLogs;
    int seconds;


    public SlidingWindowLoggerRateLimiter(int sec, int max) {
        members = new ConcurrentHashMap<>();
        seconds = sec;
        maxLogs = max;
    }

    public boolean addLog(String key, String log) {
        if (!members.containsKey(key)) {
            System.out.println("key not allowed");
            return false;
        }
        Long now = System.currentTimeMillis();
        Long previousStamp = now - (seconds * 1000);
        Queue<Long> stampsForKey = members.get(key);
        synchronized (stampsForKey) {
            while (!stampsForKey.isEmpty() && stampsForKey.peek() <= previousStamp) {
                stampsForKey.poll();
            }
            if (stampsForKey.size() >= maxLogs) {
                System.out.println("more than " + maxLogs + " in previous " + seconds + " seconds");
                System.out.println("can't add log = " + log);
                return false;
            }
            System.out.println("adding log now + " + log);
            stampsForKey.add(now);
            return true;
        }

    }

    public void  allowKey(String key) {
        if (!members.containsKey(key)) {
            members.putIfAbsent(key, new LinkedList<>());
        }
    }
}
