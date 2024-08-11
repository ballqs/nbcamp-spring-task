package com.sparta.nbcampspringtask.dto;

import lombok.Getter;

@Getter
public class ScheduleUpdateDto {
    private Long idx;
    private String content;
    private Long managerIdx;
    private String managerNm;
    private String pw;
}
