package customertimes.springParserDb.controller;

import customertimes.springParserDb.application.ArticleApp;
import customertimes.springParserDb.application.CommentApp;
import customertimes.springParserDb.dao.Article;
import customertimes.springParserDb.dao.Comment;
import customertimes.springParserDb.dto.ArticleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.stream.IntStream;

@Controller
public class ArticleViewController {

    private final ArticleApp articleApp;

    private final CommentApp commentApp;

    public ArticleViewController(ArticleApp articleApp, CommentApp commentApp) {
        this.articleApp = articleApp;
        this.commentApp = commentApp;
    }

    // refactor to REST API

    @GetMapping("/create_article")
    public String createArticle() {
        return "article/createArticle";
    }

    @GetMapping("/edit/{id}")
    public String editArticle(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleApp.getArticle(id));

        return "article/editArticle";
    }
        //remove "save"
    @PostMapping("/save_article")
    public String saveArticle(@ModelAttribute Article article) {
        articleApp.saveArticle(article.convertDto());
        return "redirect:/articles/0";
    }

    @GetMapping("/articles/{page}")                         // read about query parameter REST
    public String articles(Model model,
                           @PathVariable Integer page) {
        Page<Article> articlePage = articleApp.getArticles(PageRequest.of(page, 5));
        model.addAttribute("articlePage", articlePage);
        model.addAttribute("numbers", IntStream.range(0, articlePage.getTotalPages()).toArray());
        return "article/articles";
    }

    @PostMapping("/update_article/{id}")
    public String update(@ModelAttribute @Valid Article model, @PathVariable Long id) {
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
        comment.setArticle(new Article(article));           // move to service
        commentApp.saveComment(comment.convertDto());
        return "redirect:/articles/0";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        System.out.println("Request: " + req.getRequestURL() + " raised " + ex);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }
}


