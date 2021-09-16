package customertimes.springParserDb.dto;

import customertimes.springParserDb.dao.Article;

public class CommentDto {

    private Long id;
    private String content;
    private Article article;

    public CommentDto(Long id, String content, Article article) {
        this.id = id;
        this.content = content;
        this.article = article;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
