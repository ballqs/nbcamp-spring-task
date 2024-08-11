package com.sparta.nbcampspringtask.service;

import com.sparta.nbcampspringtask.dto.ScheduleInsertDto;
import com.sparta.nbcampspringtask.dto.ScheduleSelectDto;
import com.sparta.nbcampspringtask.entity.Schedule;
import com.sparta.nbcampspringtask.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleSelectDto createSchedule(ScheduleInsertDto scheduleInsertDto) {
        Long idx = scheduleRepository.insert(new Schedule(scheduleInsertDto));
        return new ScheduleSelectDto(scheduleRepository.findById(idx));
    }
}
