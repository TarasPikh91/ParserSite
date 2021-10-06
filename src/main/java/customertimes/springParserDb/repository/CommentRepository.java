package customertimes.springParserDb.repository;

import customertimes.springParserDb.dao.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface CommentRepository extends JpaRepository <Comment, Long> {
}
