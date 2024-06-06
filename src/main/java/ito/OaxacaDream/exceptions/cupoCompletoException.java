package ito.OaxacaDream.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class cupoCompletoException extends RuntimeException {
    public cupoCompletoException(String message) {
        super(message);
    }
}
