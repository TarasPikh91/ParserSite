package customertimes.springParserDb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No Such Article")
public class ArticlesNotFound extends RuntimeException {

    public ArticlesNotFound() {
        super("No article found");
    }
}
