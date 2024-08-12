package com.sparta.nbcampspringtask.entity;

import com.sparta.nbcampspringtask.dto.ManagerInsertDto;
import com.sparta.nbcampspringtask.dto.ScheduleUpdateDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Manager {
    private Long managerIdx;
    private String managerNm;
    private String email;
    private String regDt;
    private String modDt;

    public Manager(ManagerInsertDto managerInsertDto) {
        this.managerNm = managerInsertDto.getManagerNm();
        this.email = managerInsertDto.getEmail();
    }
}
