package ru.sunlab.meshgrouptest.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.sunlab.meshgrouptest.exception.NoRecordsException;
import ru.sunlab.meshgrouptest.exception.ProfileExistsException;
import ru.sunlab.meshgrouptest.exception.ProfileNotFoundException;

import java.util.*;

@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("msg", ex.getMessage());
        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        Map<String, Object> body = new LinkedHashMap<>();
        List<Map<String, Object>> body = new ArrayList<>();

        for(FieldError fieldError : ex.getBindingResult().getFieldErrors()){

            //Этот "if" добавлен только из-за условия в задании, так бы ошибки выводил в List
            if (fieldError.getField().equals("email")){
                Map<String, Object> email = new HashMap<>();
                email.put("msg", "field - 'email' is not correct, please retry.");
                return new ResponseEntity<>(email,HttpStatus.BAD_REQUEST);
            }

            Map<String, Object> map = new HashMap<>();
            map.put("field", fieldError.getField());
            map.put("msg", fieldError.getDefaultMessage());
            map.put("wrong_value", fieldError.getRejectedValue());
            body.add(map);
        }
        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ProfileNotFoundException.class, NoRecordsException.class})
    protected ResponseEntity<Object> handleNotFoundException(RuntimeException ex){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("msg", ex.getMessage());
        return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProfileExistsException.class)
    protected ResponseEntity<Object> handleProfileExistsException(ProfileExistsException ex){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("msg", ex.getMessage());
        return new ResponseEntity<>(body,HttpStatus.FORBIDDEN);
    }

}
