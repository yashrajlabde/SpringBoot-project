package com.springboot.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationValidationException {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleInvalidArguments(MethodArgumentNotValidException ex){

        Map<String, String> errorMap = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error-> {
            errorMap.put(error.getField(),error.getDefaultMessage());
        });

        return errorMap;
    }


    @ExceptionHandler(EmptyTitleException.class)
    public ResponseEntity<String> handleEmptytitle(EmptyTitleException emptyTitleException){
        return new ResponseEntity<String>("Title is empty, please look into it", HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<String> handleEmptyList(EmptyListException emptyListException){
        return new ResponseEntity<String>("List is empty, what are you looking for", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleEmptyList(BookNotFoundException bookNotFoundException){
        return new ResponseEntity<String>("Book is not present", HttpStatus.BAD_REQUEST);
    }

}
