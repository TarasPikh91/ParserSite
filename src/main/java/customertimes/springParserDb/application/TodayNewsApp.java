package customertimes.springParserDb.application;

import customertimes.springParserDb.dao.TodayNews;
import customertimes.springParserDb.domain.Url;
import customertimes.springParserDb.repository.TodayNewsRepository;
import customertimes.springParserDb.utils.Utils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class TodayNewsApp {

    private TodayNewsRepository newsRepository;

    public TodayNewsApp(TodayNewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public void saveTodayNews(final TodayNews todayNews) {
        newsRepository.save(todayNews);
    }

    public Optional<TodayNews> getTodayNews(final String date, final String siteKey) {
        return newsRepository.findByNewsDateAndSiteKey(date, siteKey);
    }

    public boolean isTodayNewsInBase(final Url url) {
        return getTodayNews(Utils.parseDateToString(new Date()), url.getValidUrlKey()).isEmpty();
    }

    public List<String> getTodayNewsFromBase(final Url url) {
        final TodayNews todayNews = getTodayNews(Utils.parseDateToString(new Date()), url.getValidUrlKey()).get();
        return List.of(todayNews.getNews().split("/n"));

    }

}
