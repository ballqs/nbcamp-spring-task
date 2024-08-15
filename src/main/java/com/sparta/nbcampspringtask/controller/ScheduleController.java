package com.sparta.nbcampspringtask.controller;

import com.sparta.nbcampspringtask.dto.*;
import com.sparta.nbcampspringtask.service.ScheduleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * 일정 등록 메서드. 주어진 정보를 기반으로 새로운 일정을 등록합니다.
     *
     * @param scheduleInsertDto 일정을 등록하기 위한 데이터(내용, 관리자 인덱스, 비밀번호)를 담는 DTO
     * @return 등록된 일정 정보와 성공 메시지를 포함하는 ResponseEntity 객체
     * @author 황호진
     */
    @PostMapping("/create")
    public ResponseEntity<ResponseDto<ScheduleSelectDto>> createSchedule(@Valid @RequestBody ScheduleInsertDto scheduleInsertDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(HttpStatus.OK.value(), scheduleService.createSchedule(scheduleInsertDto) , "성공적으로 등록완료했습니다."));
    }

    /**
     * 일정 조회 메서드. 주어진 인덱스를 기반으로 특정 일정을 조회합니다.
     *
     * @param idx 조회할 일정의 인덱스 (식별자)
     * @return 조회된 일정 정보와 성공 메시지를 포함하는 ResponseEntity 객체
     * @author 황호진
     */
    @GetMapping("/select")
    public ResponseEntity<ResponseDto<ScheduleSelectDto>> selectSchedule(@RequestParam Long idx) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(HttpStatus.OK.value(), scheduleService.selectSchedule( idx) , "성공적으로 조회완료했습니다."));
    }

    /**
     * 조건부 일정 조회 메서드. 주어진 조건에 따라 일정을 조회하며, 모든 조건이 선택 사항입니다.
     *
     * @param managerNm 관리자의 이름 (선택 사항)
     * @param modDt 일정의 수정 날짜 (선택 사항)
     * @param pageNum 페이지 번호 (선택 사항)
     * @param pageSize 페이지 크기 (선택 사항)
     * @return 조건에 따라 조회된 일정 목록과 성공 메시지를 포함하는 ResponseEntity 객체
     * @author 황호진
     */
    @GetMapping("/select-conditions-all")
    public ResponseEntity<ResponseDto<List<ScheduleSelectDto>>> selectConditionsAllSchedule(
                                                    @RequestParam(required = false) String managerNm ,
                                                    @RequestParam(required = false) String modDt ,
                                                    @RequestParam(required = false) Integer pageNum ,
                                                    @RequestParam(required = false) Integer pageSize) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(HttpStatus.OK.value(), scheduleService.selectConditionsAllSchedule(managerNm , modDt , pageNum , pageSize) , "성공적으로 조회완료했습니다."));
    }

    /**
     * 일정 수정 메서드. 주어진 데이터를 기반으로 특정 일정을 수정합니다.
     *
     * @param  scheduleUpdateDto 일정을 수정하기 위한 데이터(DTO)
     * @return 수정된 일정 정보와 성공 메시지를 포함하는 ResponseEntity 객체
     * @author 황호진
     */
    @PatchMapping("/update")
    public ResponseEntity<ResponseDto<ScheduleSelectDto>> updateSchedule(@Valid @RequestBody ScheduleUpdateDto scheduleUpdateDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(HttpStatus.OK.value(), scheduleService.updateSchedule(scheduleUpdateDto) , "성공적으로 수정완료했습니다."));
    }

    /**
     * 일정 삭제 메서드. 주어진 데이터를 기반으로 특정 일정을 삭제합니다.
     *
     * @param scheduleDeleteDto 일정을 삭제하기 위한 데이터(DTO)
     * @return 삭제 작업의 성공 메시지를 포함하는 ResponseEntity 객체
     * @author 황호진
     */
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto<String>> deleteSchedule(@Valid @RequestBody ScheduleDeleteDto scheduleDeleteDto) {
        scheduleService.deleteSchedule(scheduleDeleteDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(HttpStatus.OK.value(), "" , "성공적으로 삭제완료했습니다."));
    }

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
