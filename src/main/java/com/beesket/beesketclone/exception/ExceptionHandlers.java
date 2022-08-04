package com.beesket.beesketclone.exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@ControllerAdvice 는 프로젝트 전역에서 발생하는 모든 예외를 잡아줍니다.
//@ExceptionHandler 는 발생한 특정 예외를 잡아서 하나의 메소드에서 공통 처리해줄 수 있게 해줍니다.
//따라서 둘을 같이 사용하면 모든 예외를 잡은 후에 Exception 종류별로 메소드를 공통 처리할 수 있습니다.
@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e){
        ErrorResponse response = ErrorResponse.of(e.getErrorCode());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}