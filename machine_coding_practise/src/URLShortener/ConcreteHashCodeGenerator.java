package URLShortener;

import java.util.UUID;

public class ConcreteHashCodeGenerator implements HashCodeGenerator {
    public String getHashCode() {
        return "" + System.currentTimeMillis() + UUID.randomUUID();
    }
}