package customertimes.springParserDb.domain;

public class Url {

    public static final String OPENNET_URL_KEY = "opennet_news";
    public static final String MIRKNIG_URL_KEY = "mirknig_news";

    private final String urlString;
    private final String urlKey;

    public Url(final String urlString, final String urlKey) {
        this.urlString = checkUrl(urlString);
        this.urlKey = urlKey;
    }

    public String getValidUrl() {
        return checkUrl(this.urlString);
    }

    public String getValidUrlKey() {
        return this.urlKey;
    }

    private String checkUrl(final String url) {
        if (!(url == null || url == "" || url.startsWith("https://")))
            throw new IllegalArgumentException();
        return url;
    }
}
