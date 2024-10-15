package com.university.handler;

import com.university.exception.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        Set<String> errors = new HashSet<>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    // var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.add(errorMessage);
                });
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .validationErrors(errors)
                                .build()
                );
    }

    @ExceptionHandler(NameConflictException.class)
    public ResponseEntity<ExceptionResponse> handleException(NameConflictException exp) {
        return ResponseEntity
                .status(BusinessErrorCodes.DUPLICATE_NAME.getHttpStatus())
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BusinessErrorCodes.DUPLICATE_NAME.getCode())
                                .businessErrorDescription(BusinessErrorCodes.DUPLICATE_NAME.getDescription())
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(CourseException.class)
    public ResponseEntity<ExceptionResponse> handleException(CourseException exp) {
        return ResponseEntity
                .status(BusinessErrorCodes.DUPLICATE_COURSE_FOR_DEPARTMENT.getHttpStatus())
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BusinessErrorCodes.DUPLICATE_COURSE_FOR_DEPARTMENT.getCode())
                                .businessErrorDescription(BusinessErrorCodes.DUPLICATE_COURSE_FOR_DEPARTMENT.getDescription())
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(InstructorOfficeConflictException.class)
    public ResponseEntity<ExceptionResponse> handleException(InstructorOfficeConflictException exp) {
        return ResponseEntity
                .status(BusinessErrorCodes.DUPLICATE_INSTRUCTOR_FOR_OFFICE.getHttpStatus())
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BusinessErrorCodes.DUPLICATE_INSTRUCTOR_FOR_OFFICE.getCode())
                                .businessErrorDescription(BusinessErrorCodes.DUPLICATE_INSTRUCTOR_FOR_OFFICE.getDescription())
                                .error(exp.getMessage())
                                .build()
                );
    }
    @ExceptionHandler(LocationConflictException.class)
    public ResponseEntity<ExceptionResponse> handleException(LocationConflictException exp) {
        return ResponseEntity
                .status(BusinessErrorCodes.DUPLICATE_LOCATION_OFFICE.getHttpStatus())
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BusinessErrorCodes.DUPLICATE_LOCATION_OFFICE.getCode())
                                .businessErrorDescription(BusinessErrorCodes.DUPLICATE_LOCATION_OFFICE.getDescription())
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
