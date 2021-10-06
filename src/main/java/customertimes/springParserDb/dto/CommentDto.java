package customertimes.springParserDb.dto;

import customertimes.springParserDb.dao.Article;

public class CommentDto {

    private Long id;
    private String content;
    private Article article;

    public CommentDto(final Long id, final String content, final Article article) {
        this.id = id;
        this.content = content;
        this.article = article;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(final Article article) {
        this.article = article;
    }
}
