package com.WebScraping.ScrapingGit.api.exception;

import com.WebScraping.ScrapingGit.api.infra.exceptions.LoadPageException;
import com.WebScraping.ScrapingGit.api.infra.exceptions.ScrapingException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.http.HTTPException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(IOException.class)
    public ResponseEntity<StandardError> scrappingException(IOException e, HttpServletRequest request) {

        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro ", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

    @ExceptionHandler(InterruptedException.class)
    public ResponseEntity<StandardError> scrappingException(InterruptedException e, HttpServletRequest request) {

        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro ", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(HTTPException.class)
    public ResponseEntity<StandardError> scrappingException(HTTPException e, HttpServletRequest request) {

        StandardError err = new StandardError(System.currentTimeMillis(), e.getStatusCode(), "Erro ", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(ScrapingException.class)
    public ResponseEntity<StandardError> scrappingException(ScrapingException e, HttpServletRequest request) {

        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro ", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(LoadPageException.class)
    public ResponseEntity<StandardError> scrappingException(LoadPageException e, HttpServletRequest request) {

        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro ", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
