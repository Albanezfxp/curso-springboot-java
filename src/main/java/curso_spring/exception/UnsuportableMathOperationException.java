package curso_spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsuportableMathOperationException extends RuntimeException {

    public UnsuportableMathOperationException(String message) {

        super(message);

    }

}
