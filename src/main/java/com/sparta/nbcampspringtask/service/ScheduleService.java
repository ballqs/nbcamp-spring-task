package com.sparta.nbcampspringtask.service;

import com.sparta.nbcampspringtask.dto.ScheduleInsertDto;
import com.sparta.nbcampspringtask.dto.ScheduleSelectDto;
import com.sparta.nbcampspringtask.dto.ScheduleUpdateDto;
import com.sparta.nbcampspringtask.entity.Schedule;
import com.sparta.nbcampspringtask.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public ScheduleSelectDto selectSchedule(Long idx) {
        return new ScheduleSelectDto(scheduleRepository.findById(idx));
    }

    public List<ScheduleSelectDto> selectConditionsAllSchedule(String managerNm, String modDt) {
        return scheduleRepository.findConditionsAll(managerNm , modDt);
    }

    public ScheduleSelectDto updateSchedule(ScheduleUpdateDto scheduleUpdateDto) {
        Long idx = scheduleUpdateDto.getIdx();
        Schedule schedule = scheduleRepository.findById(idx);

        if (Objects.nonNull(schedule)) {
            // 비밀번호 검증
            if (Objects.equals(schedule.getPw() , scheduleUpdateDto.getPw())) {

                scheduleRepository.update(idx , new Schedule(scheduleUpdateDto));
                return new ScheduleSelectDto(scheduleRepository.findById(idx));
            } else {
                // 비번이 틀렸을 경우
                throw new IllegalArgumentException("선택한 일정은 존재하지 않습니다.");
            }
        } else {
            // 해당 일정이 존재하지 않을 경우
            throw new IllegalArgumentException("선택한 일정은 존재하지 않습니다.");
        }
    }
}
