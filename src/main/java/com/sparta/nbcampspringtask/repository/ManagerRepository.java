package com.sparta.nbcampspringtask.repository;

import com.sparta.nbcampspringtask.dto.ManagerSelectDto;
import com.sparta.nbcampspringtask.entity.Manager;
import com.sparta.nbcampspringtask.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ManagerRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    ManagerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long insert(Manager manager) {
        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO manager (manager_nm, email) VALUES (?, ?)";
        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, manager.getManagerNm());
                    preparedStatement.setString(2, manager.getEmail());
                    return preparedStatement;
            },
            keyHolder);
        return keyHolder.getKey().longValue();
    }

    public Manager findByIdx(Long idx) {
        // DB 조회
        String sql = """
                        SELECT manager_idx, manager_nm , email,
                                DATE_FORMAT(reg_dt , '%Y-%m-%d') AS reg_dt , 
                                IFNULL(DATE_FORMAT(mod_dt , '%Y-%m-%d'),'') AS mod_dt 
                        FROM manager 
                        WHERE manager_idx = ?
                    """;

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Manager manager = new Manager();
                manager.setManagerIdx(resultSet.getLong("manager_idx"));
                manager.setManagerNm(resultSet.getString("manager_nm"));
                manager.setEmail(resultSet.getString("email"));
                manager.setRegDt(resultSet.getString("reg_dt"));
                manager.setModDt(resultSet.getString("mod_dt"));
                return manager;
            } else {
                return null;
            }
        }, idx);
    }

    public void update(Long idx, Manager manager) {
        String sql = "UPDATE manager SET ";

        List<String> conditions = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        if (Objects.nonNull(manager.getManagerNm())) {
            conditions.add("manager_nm = ?");
            parameters.add(manager.getManagerNm());
        }
        if (!conditions.isEmpty()) {
            sql = sql + String.join(" , " , conditions);
            sql += " WHERE manager_idx = ?";
            parameters.add(idx);

            jdbcTemplate.update(sql, parameters.toArray());
        }
    }
}
