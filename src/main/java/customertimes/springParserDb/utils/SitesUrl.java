package customertimes.springParserDb.utils;

import customertimes.springParserDb.domain.Url;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SitesUrl {

    @Value("${opennet_url}")
    private String OPENNET_URL;

    @Value("${mirking_url}")
    private String MIRKNIG_URL;

    public List<Url> getSitesURL() {
        List<Url> urls = new ArrayList<>();
        urls.add(new Url(OPENNET_URL, Url.OPENNET_URL_KEY));
        urls.add(new Url(MIRKNIG_URL, Url.MIRKNIG_URL_KEY));
        return urls;
    }

    public String getLinkByKey(final String urlKey) {
        switch (urlKey) {
            case Url.OPENNET_URL_KEY:
                return OPENNET_URL;
            default:
                return MIRKNIG_URL;
        }
    }
}
