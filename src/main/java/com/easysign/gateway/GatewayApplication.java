package com.easysign.gateway;

import com.dtflys.forest.springboot.annotation.ForestScan;
import com.easysign.gateway.service.WebService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@SpringBootApplication
@ForestScan(basePackages = "com.easysign.gateway.common.api")
@Slf4j
public class GatewayApplication {


    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
