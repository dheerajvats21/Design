package URLShortener;

public interface LinkStore {
    public void storeLink(String code, String link);
    public String getLink(String code);
}
