package com.sparta.nbcampspringtask.controller;

import com.sparta.nbcampspringtask.dto.ScheduleInsertDto;
import com.sparta.nbcampspringtask.dto.ScheduleSelectDto;
import com.sparta.nbcampspringtask.service.ScheduleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ScheduleControllerTest {

    private final ScheduleService scheduleService;

    @Autowired
    ScheduleControllerTest(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Test
    @DisplayName("일정 조회 테스트")
    void selectSchedule() {
        ScheduleSelectDto scheduleSelectDto = scheduleService.selectSchedule(11L);
        System.out.println(scheduleSelectDto.getManagerNm());
    }

    @Test
    @DisplayName("일정 등록 테스트")
    void createSchedule() {
        ScheduleInsertDto scheduleInsertDto = new ScheduleInsertDto();
        ScheduleSelectDto scheduleSelectDto = scheduleService.createSchedule(scheduleInsertDto);
        System.out.println(scheduleSelectDto.getManagerNm());
    }

    @Test
    @DisplayName("조건부 일정 조회 테스트")
    void selectConditionsAllSchedule() {
        String managerNm = null;
        String modDt = null;
        Integer pageNum = null;
        Integer pageSize = null;
        List<ScheduleSelectDto> list =  scheduleService.selectConditionsAllSchedule(managerNm , modDt , pageNum , pageSize);
//        list.forEach(System.out::println);
        list.forEach((it) -> System.out.println(it.getManagerNm()));
    }
}
