package com.sparta.nbcampspringtask.dto;

import com.sparta.nbcampspringtask.entity.Manager;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ManagerSelectDto {
    private Long managerIdx;
    private String managerNm;
    private String email;
    private String regDt;
    private String modDt;

    public ManagerSelectDto(Manager manager) {
        this.managerIdx = manager.getManagerIdx();
        this.managerNm = manager.getManagerNm();
        this.email = manager.getEmail();
        this.regDt = manager.getRegDt();
        this.modDt = manager.getModDt();
    }
}
