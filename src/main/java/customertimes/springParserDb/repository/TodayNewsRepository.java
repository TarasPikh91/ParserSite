package customertimes.springParserDb.repository;

import customertimes.springParserDb.dao.TodayNews;
import org.springframework.data.repository.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TodayNewsRepository extends Repository<TodayNews, String> {

    void save(TodayNews todayNews);
//    List<TodayNews> findAll();
    Optional<TodayNews> findByNewsDateAndSiteKey(String date, String siteKey);
//    void deleteByDate(String date);
}
