package com.sparta.nbcampspringtask.controller;

import com.sparta.nbcampspringtask.dto.ScheduleInsertDto;
import com.sparta.nbcampspringtask.dto.ScheduleSelectDto;
import com.sparta.nbcampspringtask.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/create")
    public ScheduleSelectDto createSchedule(@RequestBody ScheduleInsertDto scheduleInsertDto) {
        return scheduleService.createSchedule(scheduleInsertDto);
    }
}
