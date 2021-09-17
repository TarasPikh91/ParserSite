package customertimes.springParserDb.application;

import customertimes.springParserDb.dao.Article;
import customertimes.springParserDb.dto.ArticleDto;
import customertimes.springParserDb.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Transactional
public class ArticleApp {

    @Autowired
    private ArticleRepository articleRepository;

    public Long saveArticle(final ArticleDto article) {
        return articleRepository.save(new Article(article)).getId();
    }

    public Page getArticles(Pageable pageable) {
        return (Page) articleRepository.findAll(pageable); //.stream().map(Article::convertDto).collect(Collectors.toList());
    }

    public ArticleDto getArticle(final Long id) {
        final Optional<Article> article = articleRepository.findById(id);
        isArticlePresent(article);
        return article.get().convertDto();
    }

    public void updateArticle(ArticleDto articleDto, Article article) {
        articleDto.setContent(article.getContent());
        articleDto.setComments(article.getComments());
        articleRepository.save(new Article(articleDto));
    }

    public void deleteArticle(final Long id) {
        articleRepository.deleteById(id);
    }

    private void isArticlePresent(final Optional<Article> article) {
        if (article.isEmpty()) {
            throw new NoSuchElementException();
        }
    }
}
