package com.sparta.nbcampspringtask.service;

import com.sparta.nbcampspringtask.dto.ScheduleDeleteDto;
import com.sparta.nbcampspringtask.dto.ScheduleInsertDto;
import com.sparta.nbcampspringtask.dto.ScheduleSelectDto;
import com.sparta.nbcampspringtask.dto.ScheduleUpdateDto;
import com.sparta.nbcampspringtask.entity.Manager;
import com.sparta.nbcampspringtask.entity.Schedule;
import com.sparta.nbcampspringtask.repository.ManagerRepository;
import com.sparta.nbcampspringtask.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ManagerRepository managerRepository;

    @Autowired
    ScheduleService(ScheduleRepository scheduleRepository , ManagerRepository managerRepository) {
        this.scheduleRepository = scheduleRepository;
        this.managerRepository = managerRepository;
    }

    // ScheduleAndManagerService.java
    public ScheduleSelectDto createSchedule(ScheduleInsertDto scheduleInsertDto) {
        // 해당 담당자가 존재하는 데이터인지?
        Manager manager = managerRepository.findByIdx(scheduleInsertDto.getManagerIdx());
        if (Objects.nonNull(manager)) {
            Long idx = scheduleRepository.insert(new Schedule(scheduleInsertDto));
            return new ScheduleSelectDto(scheduleRepository.findById(idx));
        } else {
            throw new NullPointerException("존재하지 않는 담당자 정보입니다.");
        }
    }

    public ScheduleSelectDto selectSchedule(Long idx) {
        Schedule schedule = scheduleRepository.findById(idx);
        if (Objects.nonNull(schedule)) {
            return new ScheduleSelectDto(schedule);
        } else {
            throw new NullPointerException("존재하지 않는 일정 정보입니다.");
        }
    }

    public List<ScheduleSelectDto> selectConditionsAllSchedule(String managerNm, String modDt, int pageNum, int pageSize) {
        return scheduleRepository.findConditionsAll(managerNm , modDt , pageNum , pageSize);
    }

    public ScheduleSelectDto updateSchedule(ScheduleUpdateDto scheduleUpdateDto) {
        Long idx = scheduleUpdateDto.getIdx();
        Schedule schedule = scheduleRepository.findById(idx);

        if (Objects.nonNull(schedule)) {
            // 비밀번호 검증
            if (Objects.equals(schedule.getPw() , scheduleUpdateDto.getPw())) {

                if (Objects.nonNull(scheduleUpdateDto.getManagerIdx())) {
                    Manager manager = managerRepository.findByIdx(scheduleUpdateDto.getManagerIdx());
                    if (Objects.isNull(manager)) {
                        // 해당 담당자는 존재하지 않음!
                        throw new IllegalArgumentException("입력값이 적합하지 않습니다.");
                    }
                }

                scheduleRepository.update(idx , new Schedule(scheduleUpdateDto));

                return new ScheduleSelectDto(scheduleRepository.findById(idx));
            } else {
                // 비번이 틀렸을 경우
                throw new IllegalArgumentException("입력값이 적합하지 않습니다.");
            }
        } else {
            // 해당 일정이 존재하지 않을 경우
            throw new NullPointerException("선택한 일정은 존재하지 않습니다.");
        }
    }

    public void deleteSchedule(ScheduleDeleteDto scheduleDeleteDto) {
        Long idx = scheduleDeleteDto.getIdx();
        Schedule schedule = scheduleRepository.findById(idx);

        if (Objects.nonNull(schedule)) {
            // 비밀번호 검증
            if (Objects.equals(schedule.getPw() , scheduleDeleteDto.getPw())) {

                scheduleRepository.delete(idx);
            } else {
                // 비번이 틀렸을 경우
                throw new IllegalArgumentException("입력값이 적합하지 않습니다.");
            }
        } else {
            // 해당 일정이 존재하지 않을 경우
            throw new NullPointerException("선택한 일정은 존재하지 않습니다.");
        }
    }
}
