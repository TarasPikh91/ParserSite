package customertimes.springParserDb.repository;

import customertimes.springParserDb.dao.Article;
import customertimes.springParserDb.dao.TodayNews;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends Repository<Article, Long> {

    Article save(Article article);
    List<Article> findAll();
    Optional<Article> findById(Long id);
    void deleteById(Long id);
}
