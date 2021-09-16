package customertimes.springParserDb.service.newsGeneratorService;

import customertimes.springParserDb.domain.NewsData;
import customertimes.springParserDb.domain.Url;
import customertimes.springParserDb.domain.WebData;

public interface NewsService {

    NewsData parseTodayNews(final WebData content, final Url url);
}
