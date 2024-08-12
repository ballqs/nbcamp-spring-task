![header](https://capsule-render.vercel.app/api?text=나만의%20일정%20관리%20앱%20시스템&animation=fadeIn&type=venom&color=FFA7A7&fontColor=F15F5F)

## 🌟프로젝트 개요

이 프로젝트는 Spring MVC 패턴을 이용했고 SQL를 통해 Database에 일정 및 담당자 정보를 저장 관리하는 시스템입니다.

DB에 접근하기 위해 사용 된 Connect는 JdbcTemplate 입니다.

---

## 💻기술 스택

- Java 17
- Spring Boot 3.3.2
- Spring Boot Validation
- Spring Boot JDBC
- Mysql
- Lombok
- Git

---

## 🗂 API 명세서

### 일정 등록

일정을 등록합니다.

##### Request method

`POST` 요청 

##### Request fields

| Path           | Type     | Description     | Required |
|----------------|----------|-----------------|----------|
| `content`      | `String` | 일정 내용        | 필수     |
| `managerIdx`   | `Long`   | 담당자 고유식별키 | 필수     |
| `pw`           | `String` | 비밀번호         | 필수     |

##### Example request

``` http request
POST http://localhost:9090/api/schedule/create
Content-Type: application/json

{
  "content": "일정 내용",
  "managerIdx": 1,
  "pw": "비밀번호"
}
```

##### Response fields

| Path1    | Path2        | Type        | Description     |
|----------|--------------|-------------|-----------------|
| `status` |              | `int`       | HttpStatus      |
| `body`   |              | `Object`    |                 |
|          | `idx`        | `Long`      | 일정 고유식별키   |
|          | `content`    | `String`    | 일정 내용        |
|          | `managerIdx` | `Long`      | 담당자 고유식별키 |
|          | `managerNm`  | `String`    | 담당자 명        |
|          | `regDt`      | `String`    | 등록일           |
|          | `modDt`      | `String`    | 수정일           |
| `msg`    |              | `String`    | 응답 메세지      |


##### Example response

``` http response
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Keep-Alive: timeout=60
Connection: keep-alive
{
    "status": 200,
    "body": {
        "idx": 15,
        "content": "일정 내용",
        "managerIdx": 1,
        "managerNm": "담당자",
        "regDt": "2024-08-12",
        "modDt": "2024-08-12"
    },
    "msg": "성공적으로 등록완료했습니다."
}
```

### 일정 단건 조회

일정 하나를 조회합니다.

##### Request method

`GET` 요청 

##### Request fields

| Path           | Type     | Description     | Required |
|----------------|----------|-----------------|----------|
| `idx`          | `Long`   | 일정 고유식별키   | 필수     |

##### Example request

``` http request
GET http://localhost:9090/api/schedule/select?idx=15
Content-Type: application/json
```

##### Response fields

| Path1    | Path2        | Type        | Description     |
|----------|--------------|-------------|-----------------|
| `status` |              | `int`       | HttpStatus      |
| `body`   |              | `Object`    |                 |
|          | `idx`        | `Long`      | 일정 고유식별키   |
|          | `content`    | `String`    | 일정 내용        |
|          | `managerIdx` | `Long`      | 담당자 고유식별키 |
|          | `managerNm`  | `String`    | 담당자 명        |
|          | `regDt`      | `String`    | 등록일           |
|          | `modDt`      | `String`    | 수정일           |
| `msg`    |              | `String`    | 응답 메세지      |


##### Example response

``` http response
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Keep-Alive: timeout=60
Connection: keep-alive
{
    "status": 200,
    "body": {
        "idx": 15,
        "content": "일정 내용",
        "managerIdx": 1,
        "managerNm": "담당자",
        "regDt": "2024-08-12",
        "modDt": "2024-08-12"
    },
    "msg": "성공적으로 조회완료했습니다."
}
```

### 일정 조건에 의한 전체 조회

일정을 조건에 따라 전체를 조회합니다.

##### Request method

`GET` 요청 

##### Request fields

| Path           | Type     | Description     | Required |
|----------------|----------|-----------------|----------|
| `modDt`        | `String` | 수정일           | 선택     |
| `managerNm`    | `String` | 담당자 명        | 선택     |
| `pageNum`      | `Long`   | 페이지 번호      | 선택     |
| `pageSize`     | `Long`   | 페이지 크기      | 선택     |

##### Example request

``` http request
GET http://localhost:9090/api/schedule/select-conditions-all?modDt=2024-08-11&managerNm=담당자&pageNum=1&pageSize=10
Content-Type: application/json
```

##### Response fields

| Path1    | Path2        | Type        | Description     |
|----------|--------------|-------------|-----------------|
| `status` |              | `int`       | HttpStatus      |
| `body`   |              | `Object`    |                 |
|          | `idx`        | `Long`      | 일정 고유식별키   |
|          | `content`    | `String`    | 일정 내용        |
|          | `managerIdx` | `Long`      | 담당자 고유식별키 |
|          | `managerNm`  | `String`    | 담당자 명        |
|          | `regDt`      | `String`    | 등록일           |
|          | `modDt`      | `String`    | 수정일           |
| `msg`    |              | `String`    | 응답 메세지      |


##### Example response

``` http response
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Keep-Alive: timeout=60
Connection: keep-alive
{
    "status": 200,
    "body": [
      {
          "idx": 15,
          "content": "일정 내용",
          "managerIdx": 1,
          "managerNm": "담당자",
          "regDt": "2024-08-12",
          "modDt": "2024-08-12"
      }
    ],
    "msg": "성공적으로 조회완료했습니다."
}
```

### 일정 수정

일정을 수정합니다.

##### Request method

`PATCH` 요청 

##### Request fields

| Path           | Type     | Description     | Required |
|----------------|----------|-----------------|----------|
| `idx`          | `Long`   | 일정 고유식별키   | 필수     |
| `content`      | `String` | 일정 내용        | 선택     |
| `managerIdx`   | `Long`   | 담당자 고유식별키 | 선택     |
| `pw`           | `String` | 비밀번호         | 필수     |

##### Example request

``` http request
PATCH http://localhost:9090/api/schedule/update
Content-Type: application/json
{
    "idx":11,
    "content": "변경한내용",
    "managerIdx": 2,
    "pw": "내용3"
}
```

##### Response fields

| Path1    | Path2        | Type        | Description     |
|----------|--------------|-------------|-----------------|
| `status` |              | `int`       | HttpStatus      |
| `body`   |              | `Object`    |                 |
|          | `idx`        | `Long`      | 일정 고유식별키   |
|          | `content`    | `String`    | 일정 내용        |
|          | `managerIdx` | `Long`      | 담당자 고유식별키 |
|          | `managerNm`  | `String`    | 담당자 명        |
|          | `regDt`      | `String`    | 등록일           |
|          | `modDt`      | `String`    | 수정일           |
| `msg`    |              | `String`    | 응답 메세지      |


##### Example response

``` http response
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Keep-Alive: timeout=60
Connection: keep-alive
{
    "status": 200,
    "body": {
        "idx": 11,
        "content": "변경한내용",
        "managerIdx": 2,
        "managerNm": "담당자2",
        "regDt": "2024-08-12",
        "modDt": "2024-08-12"
    },
    "msg": "성공적으로 수정완료했습니다."
}
```

### 일정 삭제

일정을 삭제합니다.

##### Request method

`DELETE` 요청 

##### Request fields

| Path           | Type     | Description     | Required |
|----------------|----------|-----------------|----------|
| `idx`          | `Long`   | 일정 고유식별키   | 필수     |
| `pw`           | `String` | 비밀번호         | 필수     |

##### Example request

``` http request
DELETE http://localhost:9090/api/schedule/delete
Content-Type: application/json
{
    "idx" : 15,
    "pw"  : "142"
}
```

##### Response fields

| Path1    | Path2        | Type        | Description     |
|----------|--------------|-------------|-----------------|
| `status` |              | `int`       | HttpStatus      |
| `body`   |              | `String`    |                 |
| `msg`    |              | `String`    | 응답 메세지      |


##### Example response

``` http response
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Keep-Alive: timeout=60
Connection: keep-alive
{
    "status": 200,
    "body": "",
    "msg": "성공적으로 삭제완료했습니다."
}
```

### 담당자 등록

담당자를 등록합니다.

##### Request method

`POST` 요청 

##### Request fields

| Path           | Type     | Description     | Required |
|----------------|----------|-----------------|----------|
| `managerNm`    | `String` | 담당자명         | 필수     |
| `email`        | `String` | 이메일           | 필수     |

##### Example request

``` http request
POST http://localhost:9090/api/manager/create
Content-Type: application/json

{
    "managerNm" : "담당자",
    "email"      : "test@test.com"
}
```

##### Response fields

| Path1    | Path2        | Type        | Description     |
|----------|--------------|-------------|-----------------|
| `status` |              | `int`       | HttpStatus      |
| `body`   |              | `Object`    |                 |
|          | `managerIdx` | `Long`      | 담당자 고유식별키 |
|          | `managerNm`  | `String`    | 담당자 명        |
|          | `email`      | `String`    | 이메일           |
|          | `regDt`      | `String`    | 등록일           |
|          | `modDt`      | `String`    | 수정일           |
| `msg`    |              | `String`    | 응답 메세지      |


##### Example response

``` http response
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Keep-Alive: timeout=60
Connection: keep-alive
{
    "status": 200,
    "body": {
        "managerIdx": 7,
        "managerNm": "담당자",
        "email": "test@test.com",
        "regDt": "2024-08-12",
        "modDt": "2024-08-12"
    },
    "msg": "성공적으로 등록완료했습니다."
}
```

---

## ⚙ ERD

![스프링 개인 과제](https://github.com/user-attachments/assets/bf77f1cf-233f-4130-8c6c-040d99dfc090)

---

## ⚙ SQL

```SQL
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
```

## 💿 파일 및 디렉토리

```
com/
└── sparta/
    └── nbcampspringtask/
        ├── controller/
        │   ├── ManagerController.java
        │   └── ScheduleController.java
        ├── dto/
        │   ├── ManagerInsertDto.java
        │   ├── ManagerSelectDto.java
        │   ├── ResponseDto.java
        │   ├── ScheduleDeleteDto.java
        │   ├── ScheduleInsertDto.java
        │   ├── ScheduleSelectDto.java
        │   └── ScheduleUpdateDto.java
        ├── entity/
        │   ├── Manager.java
        │   └── Schedule.java
        ├── repository/
        │   ├── ManagerRepository.java
        │   └── ScheduleRepository.java
        ├── service/
        │   ├── ManagerService.java
        │   └── ScheduleService.java
        └── NbcampSpringTaskApplication.java
```