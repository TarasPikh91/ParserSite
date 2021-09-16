package customertimes.springParserDb.domain;


import java.util.List;
import java.util.stream.Collectors;

public class NewsData {

    private final String news;


    public NewsData(final List<String> news) {
        this.news = news.stream().collect(Collectors.joining());
    }

    public String getTodayNews() {
        return this.news;
    }
}
