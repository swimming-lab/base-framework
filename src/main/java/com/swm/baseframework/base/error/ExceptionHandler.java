package com.swm.baseframework.base.error;

import com.swm.baseframework.base.dto.ErrorResponse;
import com.swm.baseframework.base.error.exception.IllegalItemIdException;
import com.swm.baseframework.base.error.exception.SoldOutException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        ErrorResponse errorDto = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.badRequest().body(errorDto);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalItemIdException.class)
    public ResponseEntity<ErrorResponse> illegalItemIdExceptionHandler(IllegalItemIdException e) {
        ErrorResponse errorDto = new ErrorResponse(e.getOrderErrorCode().getCode(), e.getDetailMessage());
        return ResponseEntity.badRequest().body(errorDto);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(SoldOutException.class)
    public ResponseEntity<ErrorResponse> soldOutExceptionHandler(SoldOutException e) {
        ErrorResponse errorDto = new ErrorResponse(e.getOrderErrorCode().getCode(), e.getDetailMessage());
        return ResponseEntity.internalServerError().body(errorDto);
    }
}
