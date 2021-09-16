package customertimes.springParserDb.controller;

import customertimes.springParserDb.application.ArticleApp;
import customertimes.springParserDb.application.CommentApp;
import customertimes.springParserDb.dao.Article;
import customertimes.springParserDb.dao.Comment;
import customertimes.springParserDb.dto.ArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ArticleController {

    @Autowired
    private ArticleApp articleApp;

    @Autowired
    private CommentApp commentApp;


    @GetMapping("/create_article")
    public String createArticle() {
        return "createArticle";
    }

    @GetMapping("/edit/{id}")
    public String editArticle(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleApp.getArticle(id));

        return "editArticle";
    }

    @PostMapping("/save_article")
    public String saveArticle(@ModelAttribute Article article) {
        articleApp.saveArticle(article.convertDto());
        return "redirect:/articles";
    }

    @GetMapping("/articles")
    public String articles(Model model) {
        model.addAttribute("articles", articleApp.getArticles());
        return "articles";
    }

    @PostMapping("/update_article/{id}")
    public String update(@ModelAttribute Article model, @PathVariable Long id) {
        ArticleDto article = articleApp.getArticle(id);
        article.setContent(model.getContent());
        articleApp.saveArticle(article);
        return "redirect:/articles";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        articleApp.deleteArticle(id);
        return "redirect:/articles";
    }

    @GetMapping("/add_comment/{id}")
    public String addComment(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleApp.getArticle(id));
        return "addComment";
    }

    @PostMapping("/save_comment/{id}")
    public String saveComment(@PathVariable Long id, @ModelAttribute Comment comment) {
        final ArticleDto article = articleApp.getArticle(id);
        comment.setArticle(new Article(article));
        commentApp.saveComment(comment.convertDto());
        return "redirect:/articles";
    }
}


