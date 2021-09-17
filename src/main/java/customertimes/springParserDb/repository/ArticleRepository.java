package customertimes.springParserDb.repository;

import customertimes.springParserDb.dao.Article;
import customertimes.springParserDb.dao.TodayNews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {

    Article save(Article article);
    Page <Article> findAll(Pageable pageable);
    Optional<Article> findById(Long id);
    void deleteById(Long id);
}
