package com.university.handler;

import com.university.exception.ContactConflictException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ContactConflictException.class)
    public ResponseEntity<ExceptionResponse> handleException(ContactConflictException exp) {
        return ResponseEntity
                .status(BusinessErrorCodes.DUPLICATE_CONTACT.getHttpStatus())
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BusinessErrorCodes.DUPLICATE_CONTACT.getCode())
                                .businessErrorDescription(BusinessErrorCodes.DUPLICATE_CONTACT.getDescription())
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(EntityNotFoundException exp) {
        return ResponseEntity
                .status(BusinessErrorCodes.ENTITY_NOT_FOUND.getHttpStatus())
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BusinessErrorCodes.ENTITY_NOT_FOUND.getCode())
                                .businessErrorDescription(BusinessErrorCodes.ENTITY_NOT_FOUND.getDescription())
                                .error(exp.getMessage())
                                .build()
                );
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exp) {
        exp.printStackTrace();
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorDescription("Erreur interne, veuillez contacter l'administrateur")
                                .error(exp.getMessage())
                                .build()
                );
    }
}
