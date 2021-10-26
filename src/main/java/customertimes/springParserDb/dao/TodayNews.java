package customertimes.springParserDb.dao;

import javax.persistence.*;

@Entity
@Table(name = "today_news")
public class TodayNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "site_key")
    private String siteKey;

    @Column(name = "news_date")
    private String newsDate;

    @Column(name = "news")
    private String news;

    public TodayNews () {}

    public TodayNews(final String siteKey, final String newsDate, final String news) {
        this.siteKey = siteKey;
        this.newsDate = newsDate;
        this.news = news;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getSiteKey() {
        return siteKey;
    }

    public void setSiteKey(final String siteKey) {
        this.siteKey = siteKey;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(final String newsDate) {
        this.newsDate = newsDate;
    }

    public String getNews() {
        return news;
    }

    public void setNews(final String news) {
        this.news = news;
    }
}
