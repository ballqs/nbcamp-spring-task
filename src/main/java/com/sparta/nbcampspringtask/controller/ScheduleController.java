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

    @PostMapping("/create")
    public ResponseEntity<ResponseDto<ScheduleSelectDto>> createSchedule(@Valid @RequestBody ScheduleInsertDto scheduleInsertDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(HttpStatus.OK.value(), scheduleService.createSchedule(scheduleInsertDto) , "성공적으로 등록완료했습니다."));
    }

    @GetMapping("/select")
    public ResponseEntity<ResponseDto<ScheduleSelectDto>> selectSchedule(@RequestParam Long idx) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(HttpStatus.OK.value(), scheduleService.selectSchedule( idx) , "성공적으로 조회완료했습니다."));
    }

    @GetMapping("/select-conditions-all")
    public ResponseEntity<ResponseDto<List<ScheduleSelectDto>>> selectConditionsAllSchedule(
                                                    @RequestParam(required = false) String managerNm ,
                                                    @RequestParam(required = false) String modDt ,
                                                    @RequestParam(required = false) int pageNum ,
                                                    @RequestParam(required = false) int pageSize) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(HttpStatus.OK.value(), scheduleService.selectConditionsAllSchedule(managerNm , modDt , pageNum , pageSize) , "성공적으로 조회완료했습니다."));
    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseDto<ScheduleSelectDto>> updateSchedule(@Valid @RequestBody ScheduleUpdateDto scheduleUpdateDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(HttpStatus.OK.value(), scheduleService.updateSchedule(scheduleUpdateDto) , "성공적으로 수정완료했습니다."));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto<String>> deleteSchedule(@Valid @RequestBody ScheduleDeleteDto scheduleDeleteDto) {
        scheduleService.deleteSchedule(scheduleDeleteDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(HttpStatus.OK.value(), "" , "성공적으로 삭제완료했습니다."));
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ResponseDto<String>> illegalArgumentHandle(IllegalArgumentException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseDto<>(HttpStatus.FORBIDDEN.value() , "" , e.getMessage()));
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ResponseDto<String>> nullPointerHandle(NullPointerException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto<>(HttpStatus.NOT_FOUND.value() , "" , e.getMessage()));
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
