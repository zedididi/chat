package cn.edu.ncu.bootwebsocketmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("cn.edu.ncu.bootwebsocketmybatis.dao")
@EnableCaching
public class BootWebsocketMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootWebsocketMybatisApplication.class, args);
    }

}

