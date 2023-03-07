package com.zhen777.www.sharding.datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.zhen777.www.sharding.datasource.mapper")
public class Application {
}
