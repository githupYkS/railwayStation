package com.gx.railwaystation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

import java.io.IOException;

@SpringBootApplication
@MapperScan("com.gx.railwaystation.mapper")
public class RailwayStationApplication {

    public static void main(String[] args) {
        SpringApplication.run(RailwayStationApplication.class, args);

        System.out.println("呼呼售票火车站系统启动");
        try {
            Runtime.getRuntime().exec("cmd   /c  start  http://localhost:8080/login");
        } catch (IOException e) {
            System.out.println("html异常"+e.getMessage());
            e.printStackTrace();
        }
    }

}
