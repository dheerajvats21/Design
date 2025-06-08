package URLShortener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcreteLinkStore implements LinkStore {
    Map<String, String> codeToLink = new ConcurrentHashMap<>();

    public void storeLink(String code, String link) {
        codeToLink.putIfAbsent(code, link);
    }

    public String getLink(String code) {
        return codeToLink.get(code);
    }

}
