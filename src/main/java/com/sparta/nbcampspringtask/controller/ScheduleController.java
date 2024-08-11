package com.sparta.nbcampspringtask.controller;

import com.sparta.nbcampspringtask.dto.ScheduleInsertDto;
import com.sparta.nbcampspringtask.dto.ScheduleSelectDto;
import com.sparta.nbcampspringtask.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/schedule")
    public ScheduleSelectDto createSchedule(@RequestBody ScheduleInsertDto scheduleInsertDto) {
        return scheduleService.createSchedule(scheduleInsertDto);
    }

    @GetMapping("/schedule")
    public ScheduleSelectDto selectSchedule(@RequestParam Long idx) {
        return scheduleService.selectSchedule(idx);
    }
}
