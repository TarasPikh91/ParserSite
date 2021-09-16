package customertimes.springParserDb.repository;

import customertimes.springParserDb.dao.Article;
import customertimes.springParserDb.dao.Comment;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends Repository<Comment, Long> {

    void save(Comment comment);
//    List<CommentApp> findAll();
    Optional<Comment> findById(Long id);
//    void deleteById(Long id);
}
