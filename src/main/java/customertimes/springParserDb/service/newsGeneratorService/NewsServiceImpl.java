package customertimes.springParserDb.service.newsGeneratorService;

import customertimes.springParserDb.domain.NewsData;
import customertimes.springParserDb.domain.Url;
import customertimes.springParserDb.domain.WebData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewsServiceImpl implements NewsService {

    private final OpenNetNewsServiceImpl openNetNewsService;

    private final MirknigNewsServiceImpl mirknigNewsService;

    public NewsServiceImpl(OpenNetNewsServiceImpl openNetNewsService, MirknigNewsServiceImpl mirknigNewsService) {
        this.openNetNewsService = openNetNewsService;
        this.mirknigNewsService = mirknigNewsService;
    }

    @Override
    public NewsData parseTodayNews(final WebData content, final Url url) {
        switch (url.getValidUrlKey()) {
            case Url.OPENNET_URL_KEY:
                return openNetNewsService.parseTodayNews(content, url);
            default:
                return mirknigNewsService.parseTodayNews(content, url);
        }
    }
}
