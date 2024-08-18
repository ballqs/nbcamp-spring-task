package com.sparta.nbcampspringtask.service;

import com.sparta.nbcampspringtask.dto.*;
import com.sparta.nbcampspringtask.entity.Schedule;
import com.sparta.nbcampspringtask.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 피드백에 의한 수정사항
    // 각각의 도메인은 각자 관리
    // 매니저 service가 매니저 repository를 di 하고, 스케줄 service는 매니저 service를 di하기
    private final ManagerService managerService;

    @Autowired
    ScheduleService(ScheduleRepository scheduleRepository , ManagerService managerService) {
        this.scheduleRepository = scheduleRepository;
        this.managerService = managerService;
    }

    // ScheduleAndManagerService.java
    public ScheduleSelectDto createSchedule(ScheduleInsertDto scheduleInsertDto) {
        // 해당 담당자가 존재하는 데이터인지?
        ManagerSelectDto managerSelectDto = managerService.selectManager(scheduleInsertDto.getManagerIdx());
        if (Objects.nonNull(managerSelectDto)) {
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

    public List<ScheduleSelectDto> selectConditionsAllSchedule(String managerNm, String modDt, Integer pageNum, Integer pageSize) {
        return scheduleRepository.findConditionsAll(managerNm , modDt , pageNum , pageSize).stream().map(ScheduleSelectDto::new).toList();
    }

    public ScheduleSelectDto updateSchedule(Long idx , ScheduleUpdateDto scheduleUpdateDto) {
        Schedule schedule = scheduleRepository.findById(idx);

        if (Objects.nonNull(schedule)) {
            // 비밀번호 검증
            if (Objects.equals(schedule.getPw() , scheduleUpdateDto.getPw())) {

                if (Objects.nonNull(scheduleUpdateDto.getManagerIdx())) {
                    ManagerSelectDto managerSelectDto = managerService.selectManager(scheduleUpdateDto.getManagerIdx());
                    if (Objects.isNull(managerSelectDto)) {
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

    public void deleteSchedule(Long idx , ScheduleDeleteDto scheduleDeleteDto) {
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
