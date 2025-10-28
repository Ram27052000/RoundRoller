package org.roundroller.roundrollerbackend.Exception;

import lombok.extern.slf4j.Slf4j;
import org.roundroller.roundrollerbackend.DTO.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ParticipantNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO>
    handleParticipantNotFoundException(ParticipantNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        ErrorResponseDTO errorResponse = new
                ErrorResponseDTO("Participant Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
