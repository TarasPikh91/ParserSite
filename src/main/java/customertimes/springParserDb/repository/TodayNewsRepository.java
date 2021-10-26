package customertimes.springParserDb.repository;

import customertimes.springParserDb.dao.TodayNews;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TodayNewsRepository extends JpaRepository <TodayNews, String> {

    Optional<TodayNews> findByNewsDateAndSiteKey(String date, String siteKey);
}
