package org.dendl.exercise.controller;

public final class ResponseDto {
    public RequestStatus status;
    public String errorMessage;
    public Object returnedResult;

    public ResponseDto(RequestStatus status, String errorMessage, Object returnedResult) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.returnedResult = returnedResult;
    }
}
