package com.sparta.nbcampspringtask.controller;

import com.sparta.nbcampspringtask.dto.ManagerInsertDto;
import com.sparta.nbcampspringtask.dto.ManagerSelectDto;
import com.sparta.nbcampspringtask.dto.ResponseDto;
import com.sparta.nbcampspringtask.service.ManagerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    /**
     * 관리자 등록 메서드. 주어진 정보를 기반으로 새로운 관리자를 등록합니다.
     *
     * @param managerInsertDto 관리자를 등록하기 위한 데이터(DTO)
     * @return 등록된 관리자 정보와 성공 메시지를 포함하는 ResponseEntity 객체
     * @author 황호진
     */
    @PostMapping("/managers")
    public ResponseEntity<ResponseDto<ManagerSelectDto>> createManager(@Valid @RequestBody ManagerInsertDto managerInsertDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(HttpStatus.OK.value(),managerService.createManager(managerInsertDto) ,"성공적으로 등록완료했습니다."));
    }

    /**
     * MethodArgumentNotValidException 처리 메서드. 관리자 등록 시 유효하지 않은 인자가 전달될 경우 발생하는 예외를 처리합니다.
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
