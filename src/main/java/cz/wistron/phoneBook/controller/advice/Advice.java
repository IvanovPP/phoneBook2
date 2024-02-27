package cz.wistron.phoneBook.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Advice {

    //define exceptions you want to handle and assign proper HTTP responses for them, or other behavior
    //interpretation of RuntimeException in this way is only for demonstration purposes
    //you can define your own exception which you then raise on certain occasion in your application and then handle here
    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<Object> handleException(RuntimeException re) {
        return ResponseEntity.badRequest().build();
    }
}
