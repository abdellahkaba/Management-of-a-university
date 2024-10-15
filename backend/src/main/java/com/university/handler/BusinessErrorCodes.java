package com.university.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;

@Getter
public enum BusinessErrorCodes {
    NO_CODE(0, NOT_IMPLEMENTED, "Aucun code"),
    DUPLICATE_CONTACT(400, HttpStatus.CONFLICT, "Le numero de téléphone existe déjà"),
    ENTITY_NOT_FOUND(404, HttpStatus.NOT_FOUND, "Entité non trouvée"),
    DUPLICATE_NAME(409, HttpStatus.CONFLICT, "Ce nom existe déjà"),
    DUPLICATE_COURSE_FOR_DEPARTMENT(409, HttpStatus.CONFLICT, "Ce cours est déjà attribué à cet departement"),
    DUPLICATE_INSTRUCTOR_FOR_OFFICE(409, HttpStatus.CONFLICT, "Cet Professeur a déjà un bureau"),
    DUPLICATE_LOCATION_OFFICE(409, HttpStatus.CONFLICT, "Ce bureau existe déjà"),
    ;
    private final int code;
    private final String description;
    private final HttpStatus httpStatus;
    BusinessErrorCodes(int code, HttpStatus status, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = status;
    }
}
