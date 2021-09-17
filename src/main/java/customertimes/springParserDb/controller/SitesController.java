package customertimes.springParserDb.controller;


import customertimes.springParserDb.application.TodayNewsApp;
import customertimes.springParserDb.dao.TodayNews;
import customertimes.springParserDb.domain.NewsData;
import customertimes.springParserDb.domain.Url;
import customertimes.springParserDb.domain.WebData;
import customertimes.springParserDb.service.downloadService.DownloadService;
import customertimes.springParserDb.service.newsGeneratorService.AbstractNewsService;
import customertimes.springParserDb.utils.SitesUrl;
import customertimes.springParserDb.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Date;

@Controller
public class SitesController {

    @Autowired
    private SitesUrl sitesUrl;

    @Autowired
    private DownloadService downloadService;

    @Autowired
    private AbstractNewsService abstractNewsService;

    @Autowired
    private TodayNewsApp todayNewsApp;

    @GetMapping("/")
    public String getMainPage() {
        return "todayNews/main";
    }

    @GetMapping("/sites")
    public String getSitesList(Model model) {
        model.addAttribute("sitesLink", sitesUrl.getSitesURL());
        return "todayNews/sites";
    }

    @GetMapping("/today_news/{urlKey}")
    public String showTodayNews(@PathVariable String urlKey, Model model) {
        final Url siteUrl = new Url(sitesUrl.getLinkByKey(urlKey), urlKey);
        if (todayNewsApp.isTodayNewsInBase(siteUrl)) {
            final WebData siteData = downloadService.download(siteUrl);
            final NewsData todayNewsData = abstractNewsService.parseTodayNews(siteData, siteUrl);

            final TodayNews news = new TodayNews(urlKey, Utils.parseDateToString(new Date()), todayNewsData.getTodayNews());
            todayNewsApp.saveTodayNews(news);
        }

        model.addAttribute("newsContent", todayNewsApp.getTodayNewsFromBase(siteUrl));
        return "todayNews/siteTodayNews";
    }
}