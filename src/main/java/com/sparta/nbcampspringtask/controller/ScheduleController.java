package com.sparta.nbcampspringtask.controller;

import com.sparta.nbcampspringtask.dto.ScheduleInsertDto;
import com.sparta.nbcampspringtask.dto.ScheduleSelectDto;
import com.sparta.nbcampspringtask.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/select")
    public ScheduleSelectDto selectSchedule(@RequestParam Long idx) {
        return scheduleService.selectSchedule(idx);
    }

    @GetMapping("/select-conditions-all")
    public List<ScheduleSelectDto> selectConditionsAllSchedule(
                                                    @RequestParam(required = false) String managerNm ,
                                                    @RequestParam(required = false) String modDt) {
        return scheduleService.selectConditionsAllSchedule(managerNm , modDt);
    }


}
