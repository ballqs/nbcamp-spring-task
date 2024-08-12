package com.sparta.nbcampspringtask.repository;

import com.sparta.nbcampspringtask.dto.ScheduleSelectDto;
import com.sparta.nbcampspringtask.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long insert(Schedule schedule) {
        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO schedule (content, manager_idx , pw) VALUES (?, ?, ?)";
        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, schedule.getContent());
                    preparedStatement.setLong(2, schedule.getManagerIdx());
                    preparedStatement.setString(3, schedule.getPw());
                    return preparedStatement;
                },
                keyHolder);

        return keyHolder.getKey().longValue();
    }

    public List<ScheduleSelectDto> findConditionsAll(String managerNm, String modDt, int pageNum, int pageSize) {
        // DB 조회
        String sql = """
                    SELECT s.idx, s.content, s.manager_idx, m.manager_nm , 
                            DATE_FORMAT(s.reg_dt , '%Y-%m-%d') AS reg_dt , 
                            IFNULL(DATE_FORMAT(s.mod_dt , '%Y-%m-%d'),'') AS mod_dt 
                    FROM schedule s
                        INNER JOIN manager m ON(m.manager_idx = s.manager_idx)
                    """;

        List<String> conditions = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        if (Objects.nonNull(managerNm)) {
            conditions.add("m.manager_nm = ?");
            parameters.add(managerNm);
        }

        if (Objects.nonNull(modDt)) {
            conditions.add("IFNULL(DATE_FORMAT(s.mod_dt , '%Y-%m-%d'),'') = ?");
            parameters.add(modDt);
        }

        sql = sql + ((conditions.isEmpty()) ? "" : " WHERE " + String.join(" AND " , conditions)) + " ORDER BY mod_dt DESC";

        if (Objects.nonNull(pageNum) && Objects.nonNull(pageSize)) {
            int start = pageSize * (pageNum - 1);
            int end = pageSize * pageNum;
            sql += " limit ? , ? ";
            parameters.add(start);
            parameters.add(end);
        }

        return jdbcTemplate.query(sql, new RowMapper<ScheduleSelectDto>() {
            @Override
            public ScheduleSelectDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                Long idx = rs.getLong("idx");
                String content = rs.getString("content");
                Long managerIdx = rs.getLong("manager_idx");
                String managerNm = rs.getString("manager_nm");
                String regDt = rs.getString("reg_dt");
                String modDt = rs.getString("mod_dt");
                return new ScheduleSelectDto(idx, content, managerIdx , managerNm, regDt, modDt);
            }
        } , parameters.toArray());
        // ★★★동적 쿼리 생성할 경우 담아놓은 컬렉터를 toArray()로 넣을 수 있음!★★★
    }

    public Schedule findById(Long idx) {
        // DB 조회
        String sql = """
                        SELECT s.idx, s.content, s.manager_idx, m.manager_nm , s.pw,
                                DATE_FORMAT(s.reg_dt , '%Y-%m-%d') AS reg_dt , 
                                IFNULL(DATE_FORMAT(s.mod_dt , '%Y-%m-%d'),'') AS mod_dt 
                        FROM schedule s
                            INNER JOIN manager m ON(m.manager_idx = s.manager_idx)
                        WHERE s.idx = ?
                    """;

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setIdx(resultSet.getLong("idx"));
                schedule.setContent(resultSet.getString("content"));
                schedule.setManagerIdx(resultSet.getLong("manager_idx"));
                schedule.setManagerNm(resultSet.getString("manager_nm"));
                schedule.setPw(resultSet.getString("pw"));
                schedule.setRegDt(resultSet.getString("reg_dt"));
                schedule.setModDt(resultSet.getString("mod_dt"));
                return schedule;
            } else {
                return null;
            }
        }, idx);
    }

    public void update(Long idx, Schedule schedule) {
        String sql = "UPDATE schedule SET ";

        List<String> conditions = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        if (Objects.nonNull(schedule.getContent())) {
            conditions.add("content = ?");
            parameters.add(schedule.getContent());
        }

        if (Objects.nonNull(schedule.getManagerIdx())) {
            conditions.add("manager_idx = ?");
            parameters.add(schedule.getManagerIdx());
        }

        if (!conditions.isEmpty()) {
            sql = sql + String.join(" , " , conditions);
            sql += " WHERE idx = ?";
            parameters.add(idx);

            jdbcTemplate.update(sql, parameters.toArray());
        }
    }

    public void delete(Long idx) {
        String sql = "DELETE FROM schedule WHERE idx = ?";
        jdbcTemplate.update(sql , idx);
    }
}
