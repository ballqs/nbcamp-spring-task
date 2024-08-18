package com.sparta.nbcampspringtask.service;

import com.sparta.nbcampspringtask.dto.ManagerInsertDto;
import com.sparta.nbcampspringtask.dto.ManagerSelectDto;
import com.sparta.nbcampspringtask.entity.Manager;
import com.sparta.nbcampspringtask.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;

    @Autowired
    ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public ManagerSelectDto createManager(ManagerInsertDto managerInsertDto) {
        Long idx = managerRepository.insert(new Manager(managerInsertDto));
        return new ManagerSelectDto(managerRepository.findByIdx(idx));
    }

    public ManagerSelectDto selectManager(Long idx) {
        return new ManagerSelectDto(managerRepository.findByIdx(idx));
    }
}
