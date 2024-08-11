package com.sparta.nbcampspringtask.controller;

import com.sparta.nbcampspringtask.dto.ManagerInsertDto;
import com.sparta.nbcampspringtask.dto.ManagerSelectDto;
import com.sparta.nbcampspringtask.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping("/create")
    public ManagerSelectDto createManager(@RequestBody ManagerInsertDto managerInsertDto) {
        return managerService.createManager(managerInsertDto);
    }

}
