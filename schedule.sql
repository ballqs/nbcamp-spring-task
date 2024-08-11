CREATE TABLE `schedule` (
                            `idx` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
                            `content` MEDIUMTEXT NULL DEFAULT '' COMMENT '할일 내용' COLLATE 'utf32_general_ci',
                            `manager_nm` VARCHAR(50) NULL DEFAULT NULL COMMENT '담당자 명' COLLATE 'utf32_general_ci',
                            `pw` VARCHAR(200) NULL DEFAULT NULL COMMENT '비밀번호(암호화 없이)' COLLATE 'utf32_general_ci',
                            `reg_dt` DATETIME NULL DEFAULT current_timestamp() COMMENT '등록일',
                            `mod_dt` DATETIME NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '수정일',
                            PRIMARY KEY (`idx`) USING BTREE
)
    COMMENT='일정'
COLLATE='utf32_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=7
;