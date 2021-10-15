package com.changgou.web;

import com.changgou.util.IdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
@ServletComponentScan
public class Main {

    @Value("${workerId}")
    private Integer workerId;

    @Value("${datacenterId}")
    private Integer datacenterId;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(workerId,datacenterId);
    }
}
