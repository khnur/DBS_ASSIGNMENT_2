package com.example.demo.controller;

import com.example.demo.dto.ErrorDto;
import com.example.demo.exception.ConflictException;
import com.example.demo.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleNotFound(HttpServletRequest httpServletRequest, RuntimeException e) {
        return handleException(httpServletRequest, e, NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleConflict(HttpServletRequest httpServletRequest, RuntimeException e) {
        return handleException(httpServletRequest, e, CONFLICT);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleValidation(HttpServletRequest httpServletRequest, RuntimeException e) {
        return handleException(httpServletRequest, e, BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleAuthentication(HttpServletRequest httpServletRequest, RuntimeException e) {
        return handleException(httpServletRequest, e, UNAUTHORIZED);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleGeneral(HttpServletRequest httpServletRequest, RuntimeException e) {
        return handleException(httpServletRequest, e, INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorDto> handleException(
            HttpServletRequest httpServletRequest,
            RuntimeException e,
            HttpStatus status
    ) {
        return ResponseEntity.status(status).body(
                ErrorDto.builder()
                        .timestamp(LocalDateTime.now())
                        .status(status.value())
                        .error(status.getReasonPhrase())
                        .path(httpServletRequest.getServletPath())
                        .message(e.getMessage())
                        .build()
        );
    }
}