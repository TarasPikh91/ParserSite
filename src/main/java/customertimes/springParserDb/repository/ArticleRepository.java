package customertimes.springParserDb.repository;

import customertimes.springParserDb.dao.Article;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {}
