package hu.acsaifz.blogapp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BlogApiException extends RuntimeException{
    private HttpStatus status;

    public BlogApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
