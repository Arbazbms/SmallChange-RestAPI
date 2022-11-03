package com.fidelity.smallchange;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
	"com.fidelity.smallchange.integration",
	"com.fidelity.smallchange.integration.mapper",
	"com.fidelity.smallchange.controller", 
	"com.fidelity.smallchange.service"
})
@MapperScan(basePackages="com.fidelity.smallchange.integration.mapper")  
public class SmallchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmallchangeApplication.class, args);
	}

}
