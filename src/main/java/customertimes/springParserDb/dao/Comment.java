package customertimes.springParserDb.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import customertimes.springParserDb.dto.CommentDto;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @Column(name="comment_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Min(10)
    @Column(name = "comment_text")
    private String content;

    @JsonIgnore
    @ManyToOne
    private Article article;

    public Comment() {}

    public Comment(final Long id, final String content, Article article) {
        this.id = id;
        this.content = content;
        this.article = article;
    }

    public Comment(final CommentDto commentDto) {
        this.id = commentDto.getId();
        this.content = commentDto.getContent();
        this.article = commentDto.getArticle();
    }

    public CommentDto convertDto() {
        return new CommentDto(this.getId(), this.getContent(), this.getArticle());
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
