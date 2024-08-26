package com.sparta.nbcampspringtask.repository;


import com.sparta.nbcampspringtask.entity.Manager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class ScheduleRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Transactional
    @Rollback
    @DisplayName("Bulk Insert 테스트")
    void Test1() {

        long startTime = System.currentTimeMillis();

        List<Manager> managerList = new ArrayList<>();

        for (int i =1; i< 100000; i++){
            Manager manager = new Manager();
            manager.setManagerNm("이름");
            manager.setEmail("이메일");

            managerList.add(manager);
        }

        String sql = "INSERT INTO manager (manager_nm, email)"+
                "VALUES (?, ?)";

        jdbcTemplate.batchUpdate(sql,
                managerList,
                managerList.size(),
                (PreparedStatement ps, Manager manager) -> {
                    ps.setString(1, manager.getManagerNm());
                    ps.setString(2, manager.getEmail());
                });

        System.out.println("taken time = "+(System.currentTimeMillis() - startTime)+"ms");
    }
}
