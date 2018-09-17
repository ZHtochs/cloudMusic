package com.tianmaying.crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @program: tmy
 * @description:
 * @author: zhuhe
 * @create: 2018-09-17 10:46
 **/
@SpringBootApplication
@EnableAsync
public class Application {

    public static void main(String[] args) throws IOException, URISyntaxException {
        SpringApplication.run(Application.class);
    }

}