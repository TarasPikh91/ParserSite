package customertimes.springParserDb.domain;

import customertimes.springParserDb.utils.Utils;

public class WebData {

    private final String webHtml;

    public WebData(final String webHtml) {
        this.webHtml = checkWebHtml(webHtml);
    }

    public String getWebData() {
        return checkWebHtml(this.webHtml);
    }

    private String checkWebHtml(final String htmlStr) {
        if (!isWebPage(htmlStr))
            throw new IllegalArgumentException();
        return htmlStr;
    }

    private boolean isWebPage(final String webData) {
        return Utils.checkWebPage(webData);
    }
}
