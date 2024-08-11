package com.sparta.nbcampspringtask.entity;

import com.sparta.nbcampspringtask.dto.ScheduleInsertDto;
import com.sparta.nbcampspringtask.dto.ScheduleUpdateDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private Long idx;
    private String content;
    private Long managerIdx;
    private String managerNm;
    private String pw;
    private String regDt;
    private String modDt;

    public Schedule(ScheduleInsertDto scheduleInsertDto) {
        this.content = scheduleInsertDto.getContent();
        this.managerIdx = scheduleInsertDto.getManagerIdx();
        this.pw = scheduleInsertDto.getPw();
    }

    public Schedule(ScheduleUpdateDto scheduleUpdateDto) {
        this.content = scheduleUpdateDto.getContent();
        this.managerIdx = scheduleUpdateDto.getManagerIdx();
    }
}
