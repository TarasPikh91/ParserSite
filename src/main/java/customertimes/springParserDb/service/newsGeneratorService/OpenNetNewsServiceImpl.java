package customertimes.springParserDb.service.newsGeneratorService;

import customertimes.springParserDb.domain.NewsData;
import customertimes.springParserDb.domain.Url;
import customertimes.springParserDb.domain.WebData;
import customertimes.springParserDb.utils.Utils;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class OpenNetNewsServiceImpl implements NewsService {

    @Override
    public NewsData parseTodayNews(final WebData siteContent, final Url url) {
        final List<String> contentList = List.of(siteContent.getWebData().split("</tr>"));
        List<String> news = new ArrayList<>();
        for(String content : contentList) {
            if(isTodayDay(content)) {
                final List<String> todayBlockElements = List.of(content.split("</td>"));
                news.add(Jsoup.parse(todayBlockElements.get(todayBlockElements.size() - 1)).text());
            }
        }
        return new NewsData(news);
    }

    private boolean isTodayDay(final String content) {
        return content.contains("<td class=tdate>" + Utils.parseDate(new Date(), Utils.OPENNET_DATE_FORMAT) + "</td>");
    }
}
