package customertimes.springParserDb.application;

import customertimes.springParserDb.dao.Comment;
import customertimes.springParserDb.dto.ArticleDto;
import customertimes.springParserDb.dto.CommentDto;
import customertimes.springParserDb.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@Transactional
public class CommentApp {

    @Autowired
    private CommentRepository commentRepository;


    public void saveComment(final CommentDto comment) {
        commentRepository.save(new Comment(comment));
    }

    public CommentDto getCommentById(final Long id) {
        final Optional<Comment> commentOptional = commentRepository.findById(id);
        isCommentPresent(commentOptional);
        return commentOptional.get().convertDto();
    }

    public Comment getCommentFromArticle(final ArticleDto articleDto, final Long commentId) {
        final Optional<Comment> articleComment = articleDto.getComments()
                                                .stream()
                                                .filter((comment) -> commentId.equals(comment.getId()))
                                                .findFirst();
        isCommentPresent(articleComment);
        return articleComment.get();
    }

    private void isCommentPresent(final Optional<Comment> comment) {
        if (!comment.isEmpty()) {
            throw new NoSuchElementException();
        }
    }
}
