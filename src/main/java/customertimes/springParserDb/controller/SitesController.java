package customertimes.springParserDb.controller;


import customertimes.springParserDb.application.TodayNewsApp;
import customertimes.springParserDb.dao.TodayNews;
import customertimes.springParserDb.domain.NewsData;
import customertimes.springParserDb.domain.Url;
import customertimes.springParserDb.domain.WebData;
import customertimes.springParserDb.service.downloadService.DownloadService;
import customertimes.springParserDb.service.newsGeneratorService.NewsServiceImpl;
import customertimes.springParserDb.utils.SitesUrl;
import customertimes.springParserDb.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Date;

@Controller
public class SitesController {

    private final SitesUrl sitesUrl;

    private final DownloadService downloadService;

    private final NewsServiceImpl newsServiceImpl;

    private final TodayNewsApp todayNewsApp;

    public SitesController(SitesUrl sitesUrl, DownloadService downloadService, NewsServiceImpl newsServiceImpl, TodayNewsApp todayNewsApp) {
        this.sitesUrl = sitesUrl;
        this.downloadService = downloadService;
        this.newsServiceImpl = newsServiceImpl;
        this.todayNewsApp = todayNewsApp;
    }

    @GetMapping("/")
    public String getLoginPage() {
        return "todayNews/main";
    }

    @GetMapping("/login")
    public String getMainPage() {
        return "login";
    }

    @PostMapping("/logout")
    public String logout() {
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
            final NewsData todayNewsData = newsServiceImpl.parseTodayNews(siteData, siteUrl);

            final TodayNews news = new TodayNews(urlKey, Utils.parseDateToString(new Date()), todayNewsData.getTodayNews());
            todayNewsApp.saveTodayNews(news);
        }

        model.addAttribute("newsContent", todayNewsApp.getTodayNewsFromBase(siteUrl));
        return "todayNews/siteTodayNews";
    }
}