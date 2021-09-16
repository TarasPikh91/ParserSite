package customertimes.springParserDb.service.downloadService;

import customertimes.springParserDb.domain.Url;
import customertimes.springParserDb.domain.WebData;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DownloadService implements WebPageDownloadService {

    WebData getSiteContentData(final Url url) {
        try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {
            final HttpGet httpGet = getGetRequest(url);
            // Create a custom response handler
            final HttpClientResponseHandler<String> responseHandler = getResponseHandler();
            return new WebData(httpclient.execute(httpGet, responseHandler));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new WebData("");
    }

    private HttpClientResponseHandler getResponseHandler() {
        return new HttpClientResponseHandler<String>() {
            @Override
            public String handleResponse(final ClassicHttpResponse response) throws IOException {
                final int status = response.getCode();
                if (status >= HttpStatus.SC_SUCCESS && status < HttpStatus.SC_REDIRECTION) {
                    final HttpEntity entity = response.getEntity();
                    try {
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } catch (final ParseException ex) {
                        throw new ClientProtocolException(ex);
                    }
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }
        };
    }

    private HttpGet getGetRequest(final Url url) {
        HttpGet httpGet = new HttpGet(url.getValidUrl());
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        return httpGet;
    }

    @Override
    public WebData download(final Url url) {
        return getSiteContentData(url);
    }
}
