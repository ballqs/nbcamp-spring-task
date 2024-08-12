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
@RequestMapping("/api/manager")
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto<ManagerSelectDto>> createManager(@Valid @RequestBody ManagerInsertDto managerInsertDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(HttpStatus.OK.value(),managerService.createManager(managerInsertDto) ,"성공적으로 등록완료했습니다."));
    }

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
