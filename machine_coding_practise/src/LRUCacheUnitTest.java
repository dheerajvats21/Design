import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class LRUCacheUnitTest {

    @Test
    public void testPutAndGet() {
        LRUCache<Integer, String> cache = new LRUCacheImpl<>(4);
        cache.put(1, "One");
        cache.put(2, "two");
        cache.put(3, "three");
        cache.put(4, "four");

        String x = cache.get(2);
        assertEquals(x, "two");
        x = cache.get(1);
        assertEquals(x, "One");

        cache.put(5, "five");
        x = cache.get(4);
        assertEquals(x, "four");

        x = cache.get(3);
        assertNull(x);

    }

    @Test
    public void testOverwrite() {
        LRUCache<Integer, String> cache = new LRUCacheImpl<>(4);
        cache.put(1, "One");
        cache.put(2, "two");
        cache.put(3, "three");
        cache.put(4, "four");

        String x = cache.get(2);
        assertEquals(x, "two");
        x = cache.get(1);
        assertEquals(x, "One");

        x = cache.get(4);
        assertEquals(x, "four");

        x = cache.get(3);
        assertEquals(x, "three");


    }
}
