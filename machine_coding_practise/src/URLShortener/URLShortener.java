package URLShortener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class URLShortener {

    private final String MY_DOMAIN = "https://www.SHORTENER.com/";

    HashCodeGenerator hashCodeGenerator;
    LinkStore linkStore;
    public URLShortener(HashCodeGenerator hashCodeGenerator, LinkStore linkStore) {
        hashCodeGenerator = hashCodeGenerator;
        linkStore = linkStore;
    }
    // take the long link and convert it to
    public String shortTheLongLink(String longlink) {
        String code = hashCodeGenerator.getHashCode();
        linkStore.storeLink(code, longlink);
        return MY_DOMAIN + code;
    }

    public String longLink(String shortLink) {
        // remove mydomain prefix from short link
        // use the hashcode and bring back the long link from it
        String code = shortLink.substring(MY_DOMAIN.length());
        String longLink = linkStore.getLink(code);
        return longLink;
    }









}
