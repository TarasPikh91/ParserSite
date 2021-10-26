package customertimes.springParserDb.application;

import customertimes.springParserDb.dao.Article;
import customertimes.springParserDb.dto.ArticleDto;
import customertimes.springParserDb.exceptions.ArticlesNotFound;
import customertimes.springParserDb.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class ArticleApp {

    final private ArticleRepository articleRepository;

    public ArticleApp(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional
    public Long saveArticle(final ArticleDto article) {
        return articleRepository.save(new Article(article)).getId();
    }

    @Transactional(readOnly = true)
    public Page getArticles(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public ArticleDto getArticle(final Long id) {
        final Optional<Article> articleOptional = articleRepository.findById(id);
        isArticlePresent(articleOptional.isPresent());

        return articleOptional.get().convertDto();
    }

    @Transactional
    public void updateArticle(ArticleDto articleDto, Article article) {
        articleDto.setContent(article.getContent());
        articleDto.setComments(article.getComments());
        articleRepository.save(new Article(articleDto));
    }

    @Transactional
    public void deleteArticle(final Long id) {
        articleRepository.deleteById(id);
    }

    private void isArticlePresent(final boolean isArticlePresent) {
        if (!isArticlePresent) {
            throw new ArticlesNotFound();
        }
    }
}
