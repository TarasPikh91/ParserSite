package customertimes.springParserDb.controller;

import customertimes.springParserDb.application.ArticleApp;
import customertimes.springParserDb.dao.Article;
import customertimes.springParserDb.dao.Comment;
import customertimes.springParserDb.dto.ArticleDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleRestController {

    final private ArticleApp articleApp;

    public ArticleRestController(ArticleApp articleApp) {
        this.articleApp = articleApp;
    }


    @GetMapping("/articles")
    public List<ArticleDto> getList(Model model,
                                    @PageableDefault Pageable pageable) {
        return articleApp.getArticles(pageable).getContent();
    }

    @GetMapping("/{id}")
    public ArticleDto getById(@PathVariable Long id, Model model) {
        return articleApp.getArticle(id);
    }

    @RequestMapping("/articles/:{articleId}/comments/:{commentId}")
    public Comment getArticleComment(@PathVariable Long articleId, @PathVariable Long commentId) {
        return articleApp.getArticle(articleId).getComments().stream().filter((comment) -> commentId.equals(comment.getId())).findFirst().get();
    }

    @GetMapping("/articles/:{articleId}/comments")
    public List<Comment> getArticleComments(@PathVariable Long articleId) {
        return articleApp.getArticle(articleId).getComments();
    }

    @PutMapping
    public Long create(@ModelAttribute Article article) {
        return articleApp.saveArticle(article.convertDto());
    }

    @PostMapping("/update/{id}")
    public void update(@ModelAttribute Article model, @PathVariable Long id) {
        articleApp.updateArticle(articleApp.getArticle(id), model);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) throws Exception {

        articleApp.deleteArticle(id);
    }
}




