package customertimes.springParserDb.dto;

import customertimes.springParserDb.dao.Comment;

import java.util.List;

public class ArticleDto {

    private Long id;
    private String content;
    private List<Comment> comments;

    public ArticleDto(Long id, String content, List<Comment> comments) {
        this.id = id;
        this.content = content;
        this.comments = comments;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
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
