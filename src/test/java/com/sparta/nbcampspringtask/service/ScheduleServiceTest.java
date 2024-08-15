package com.sparta.nbcampspringtask.service;

import com.sparta.nbcampspringtask.dto.ScheduleSelectDto;
import com.sparta.nbcampspringtask.entity.Manager;
import com.sparta.nbcampspringtask.entity.Schedule;
import com.sparta.nbcampspringtask.repository.ManagerRepository;
import com.sparta.nbcampspringtask.repository.ScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

@SpringBootTest
class ScheduleServiceTest {

    private final ScheduleRepository scheduleRepository;
    private final ManagerRepository managerRepository;

    @Autowired
    ScheduleServiceTest(ScheduleRepository scheduleRepository , ManagerRepository managerRepository) {
        this.scheduleRepository = scheduleRepository;
        this.managerRepository = managerRepository;
    }

    @Test
    @DisplayName("일정 등록 테스트")
    void createSchedule() {
        Manager manager = managerRepository.findByIdx(2L);
        if (Objects.nonNull(manager)) {
            Schedule schedule = new Schedule();
            schedule.setContent("일정내용");
            schedule.setManagerIdx(manager.getManagerIdx());
            schedule.setPw("비밀번호");
            Long id = scheduleRepository.insert(schedule);
            ScheduleSelectDto scheduleSelectDto = new ScheduleSelectDto(scheduleRepository.findById(id));
            System.out.println(scheduleSelectDto.getManagerNm());
        }
    }

    @Test
    void selectSchedule() {
        Schedule schedule = scheduleRepository.findById(11L);
        System.out.println(schedule.getManagerNm());
    }
}