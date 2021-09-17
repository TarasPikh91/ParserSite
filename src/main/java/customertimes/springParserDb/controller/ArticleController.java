package customertimes.springParserDb.controller;

import customertimes.springParserDb.application.ArticleApp;
import customertimes.springParserDb.application.CommentApp;
import customertimes.springParserDb.dao.Article;
import customertimes.springParserDb.dao.Comment;
import customertimes.springParserDb.dto.ArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.IntStream;

@Controller
public class ArticleController {

    @Autowired
    private ArticleApp articleApp;

    @Autowired
    private CommentApp commentApp;


    @GetMapping("/create_article")
    public String createArticle() {
        return "article/createArticle";
    }

    @GetMapping("/edit/{id}")
    public String editArticle(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleApp.getArticle(id));

        return "article/editArticle";
    }

    @PostMapping("/save_article")
    public String saveArticle(@ModelAttribute Article article) {
        articleApp.saveArticle(article.convertDto());
        return "redirect:/articles/0";
    }

    @GetMapping("/articles/{page}")
    public String articles(Model model,
                           @PathVariable Integer page) {
        Page<Article> articlePage = articleApp.getArticles(PageRequest.of(page, 5));
        model.addAttribute("articlePage", articlePage);
        model.addAttribute("numbers", IntStream.range(0, articlePage.getTotalPages()).toArray());
        return "article/articles";
    }

    @PostMapping("/update_article/{id}")
    public String update(@ModelAttribute Article model, @PathVariable Long id) {
        ArticleDto article = articleApp.getArticle(id);
        article.setContent(model.getContent());
        articleApp.saveArticle(article);
        return "redirect:/articles/0";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        articleApp.deleteArticle(id);
        return "redirect:/articles/0";
    }

    @GetMapping("/add_comment/{id}")
    public String addComment(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleApp.getArticle(id));
        return "article/addComment";
    }

    @PostMapping("/save_comment/{id}")
    public String saveComment(@PathVariable Long id, @ModelAttribute Comment comment) {
        final ArticleDto article = articleApp.getArticle(id);
        comment.setArticle(new Article(article));
        commentApp.saveComment(comment.convertDto());
        return "redirect:/articles/0";
    }
}


