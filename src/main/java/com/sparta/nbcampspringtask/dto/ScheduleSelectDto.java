package com.sparta.nbcampspringtask.dto;

import com.sparta.nbcampspringtask.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ScheduleSelectDto {
    private Long idx;
    private String content;
    private Long managerIdx;
    private String managerNm;
    private String regDt;
    private String modDt;

    public ScheduleSelectDto(Schedule schedule) {
        this.idx = schedule.getIdx();
        this.content = schedule.getContent();
        this.managerIdx = schedule.getManagerIdx();
        this.managerNm = schedule.getManagerNm();
        this.regDt = schedule.getRegDt();
        this.modDt = schedule.getModDt();
    }
}
