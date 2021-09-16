package customertimes.springParserDb.service.newsGeneratorService;

import customertimes.springParserDb.domain.NewsData;
import customertimes.springParserDb.domain.Url;
import customertimes.springParserDb.domain.WebData;
import customertimes.springParserDb.utils.Utils;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MirknigNewsServiceImpl implements NewsService {

    @Value("${date_pattern_contains}")
    private String DATE_PATTERN_CONTAINS;

    @Override
    public NewsData parseTodayNews(final WebData siteContent, final Url url) {
        final List<String> contentList = Arrays.asList(siteContent.getWebData().split("</table>"));
        final List<String> news = new ArrayList<>();
        for(String content : contentList) {
            if(isTodayDay(content)) {
                final List<String> todayBlockElements = Arrays.asList(content.split("</tr>"));
                news.add(Jsoup.parse(todayBlockElements.get(1)).text());
            }
        }
        return new NewsData(news);
    }

    private boolean isTodayDay(final String content) {
        return (content.contains("Сегодня, ") || Utils.ifContainsDate(content, DATE_PATTERN_CONTAINS));
    }


}
