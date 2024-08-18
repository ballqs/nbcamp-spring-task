package com.sparta.nbcampspringtask;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NbcampSpringTaskApplication {

    public static void main(String[] args) {
        // .env 파일 로드
        // 다른 위치에 있다면 Dotenv.configure().directory("/your/path").load()를 사용하여 경로를 지정
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        // 환경 변수를 시스템 속성으로 설정
        System.setProperty("DB_IP", dotenv.get("DB_IP"));
        System.setProperty("DB_PORT", dotenv.get("DB_PORT"));
        System.setProperty("DB_NM", dotenv.get("DB_NM"));
        System.setProperty("DB_ID", dotenv.get("DB_ID"));
        System.setProperty("DB_PW", dotenv.get("DB_PW"));

        SpringApplication.run(NbcampSpringTaskApplication.class, args);
    }

}
