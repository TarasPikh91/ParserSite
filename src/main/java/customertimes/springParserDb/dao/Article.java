package customertimes.springParserDb.dao;

import customertimes.springParserDb.dto.ArticleDto;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Min(10)
    private String content;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="article_id")
    private List<Comment> comments;

    public Article() {}

    public Article(final Long id, final String content, final List<Comment> comments) {
        this.id = id;
        this.content = content;
        this.comments = comments;
    }

    public Article(final ArticleDto articleDto) {
        this.id = articleDto.getId();
        this.content = articleDto.getContent();
        this.comments = articleDto.getComments();
    }

    public ArticleDto convertDto() {
        return new ArticleDto(this.getId(), this.getContent(), this.getComments());
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(final List<Comment> comments) {
        this.comments = comments;
    }
}
