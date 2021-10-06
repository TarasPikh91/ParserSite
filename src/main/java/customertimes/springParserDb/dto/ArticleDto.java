package customertimes.springParserDb.dto;

import customertimes.springParserDb.dao.Comment;

import java.util.List;

public class ArticleDto {

    private Long id;
    private String content;
    private List<Comment> comments;

    public ArticleDto(final Long id, final String content, final List<Comment> comments) {
        this.id = id;
        this.content = content;
        this.comments = comments;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(final List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "ArticleDto{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", comments=" + comments +
                '}';
    }
}
