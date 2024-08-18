package com.sparta.nbcampspringtask.controller;

import com.sparta.nbcampspringtask.dto.ManagerInsertDto;
import com.sparta.nbcampspringtask.dto.ManagerSelectDto;
import com.sparta.nbcampspringtask.dto.ResponseDto;
import com.sparta.nbcampspringtask.service.ManagerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
