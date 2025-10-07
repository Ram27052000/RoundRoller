package org.roundroller.roundrollerbackend.Exception;


public class ParticipantNotFoundException extends RuntimeException {

    public ParticipantNotFoundException() {
    }
    public ParticipantNotFoundException(String message) {
        super(message);
    }

}
