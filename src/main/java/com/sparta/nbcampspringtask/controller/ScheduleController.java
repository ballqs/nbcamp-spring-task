package com.sparta.nbcampspringtask.controller;

import com.sparta.nbcampspringtask.dto.*;
import com.sparta.nbcampspringtask.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
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
    @PostMapping("/schedules")
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
    @GetMapping("/schedules/{idx}")
    public ResponseEntity<ResponseDto<ScheduleSelectDto>> selectSchedule(@PathVariable Long idx) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(HttpStatus.OK.value(), scheduleService.selectSchedule(idx) , "성공적으로 조회완료했습니다."));
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
    @GetMapping("/schedules")
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
    @PatchMapping("/schedules/{idx}")
    public ResponseEntity<ResponseDto<ScheduleSelectDto>> updateSchedule(@PathVariable Long idx ,
                                                                         @Valid @RequestBody ScheduleUpdateDto scheduleUpdateDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(HttpStatus.OK.value(), scheduleService.updateSchedule(idx , scheduleUpdateDto) , "성공적으로 수정완료했습니다."));
    }

    /**
     * 일정 삭제 메서드. 주어진 데이터를 기반으로 특정 일정을 삭제합니다.
     *
     * @param scheduleDeleteDto 일정을 삭제하기 위한 데이터(DTO)
     * @return 삭제 작업의 성공 메시지를 포함하는 ResponseEntity 객체
     * @author 황호진
     */
    @DeleteMapping("/schedules/{idx}")
    public ResponseEntity<ResponseDto<String>> deleteSchedule(@PathVariable Long idx ,
                                                              @Valid @RequestBody ScheduleDeleteDto scheduleDeleteDto) {
        scheduleService.deleteSchedule(idx , scheduleDeleteDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(HttpStatus.OK.value(), "" , "성공적으로 삭제완료했습니다."));
    }
}
