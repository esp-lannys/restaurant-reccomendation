package spm.project.restaurantrecommendation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EntityNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler({EntityNotFoundException.class, EmailNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String entityNotFoundHandler(EntityNotFoundException ex){
        return ex.getMessage();
    }

    public String emailNotFoundHandler(EmailNotFoundException ex){
        return ex.getMessage();
    }
}
