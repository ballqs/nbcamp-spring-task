-- nbc_spring_task 데이터베이스 구조 내보내기
DROP DATABASE IF EXISTS `nbc_spring_task`;
CREATE DATABASE IF NOT EXISTS `nbc_spring_task`;
USE `nbc_spring_task`;

-- 테이블 nbc_spring_task.manager 구조 내보내기
DROP TABLE IF EXISTS `manager`;
CREATE TABLE IF NOT EXISTS `manager` (
    `manager_idx` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '담당자 IDX',
    `manager_nm` varchar(50) DEFAULT '' COMMENT '담당자 명',
    `email` varchar(50) DEFAULT '' COMMENT '담당자 이메일',
    `reg_dt` datetime DEFAULT current_timestamp() COMMENT '등록일',
    `mod_dt` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '수정일',
    PRIMARY KEY (`manager_idx`)
    ) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf32 COLLATE=utf32_general_ci COMMENT='담당자';


-- 테이블 nbc_spring_task.schedule 구조 내보내기
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE IF NOT EXISTS `schedule` (
    `idx` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
    `content` mediumtext DEFAULT '' COMMENT '할일 내용',
    `manager_idx` bigint(20) DEFAULT NULL COMMENT '담당자 IDX(manager.pk)',
    `pw` varchar(200) DEFAULT NULL COMMENT '비밀번호(암호화 없이)',
    `reg_dt` datetime DEFAULT current_timestamp() COMMENT '등록일',
    `mod_dt` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '수정일',
    PRIMARY KEY (`idx`)
    ) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf32 COLLATE=utf32_general_ci COMMENT='일정';