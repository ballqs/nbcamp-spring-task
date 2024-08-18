package com.sparta.nbcampspringtask.controller;

import com.sparta.nbcampspringtask.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionController {

    /**
     * IllegalArgumentException 처리 메서드. 잘못된 인자가 전달되었을 때 예외를 처리합니다.
     *
     * @param e 처리할 IllegalArgumentException 객체
     * @return FORBIDDEN 상태 코드와 예외 메시지를 포함하는 ResponseEntity 객체
     * @author 황호진
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ResponseDto<String>> illegalArgumentHandle(IllegalArgumentException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseDto<>(HttpStatus.FORBIDDEN.value() , "" , e.getMessage()));
    }

    /**
     * NullPointerException 처리 메서드. Null 값이 발생할 때 예외를 처리합니다.
     *
     * @param e 처리할 NullPointerException 객체
     * @return NOT_FOUND 상태 코드와 예외 메시지를 포함하는 ResponseEntity 객체
     * @author 황호진
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ResponseDto<String>> nullPointerHandle(NullPointerException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto<>(HttpStatus.NOT_FOUND.value() , "" , e.getMessage()));
    }

    /**
     * MethodArgumentNotValidException 처리 메서드. 메서드 인자가 유효하지 않을 때 발생하는 예외를 처리합니다.
     *
     * @param e 처리할 MethodArgumentNotValidException 객체
     * @return BAD_REQUEST 상태 코드와 유효성 검사 오류 메시지 목록을 포함하는 ResponseEntity 객체
     * @author 황호진
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<String>> methodArgumentNotValidHabdle(MethodArgumentNotValidException e) {
        List<String> msgList = new ArrayList<>();
        for (int i = 0; i < e.getBindingResult().getFieldErrors().size(); i++) {
            String msg = e.getBindingResult().getFieldErrors().get(i).getField() + "는 " + e.getBindingResult().getFieldErrors().get(i).getDefaultMessage();
            msgList.add(msg);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto<>(HttpStatus.BAD_REQUEST.value() , "" , String.join(" , " , msgList)));
    }

}
