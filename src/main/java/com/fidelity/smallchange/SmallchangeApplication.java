package com.fidelity.smallchange;


import org.mybatis.spring.annotation.MapperScan;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import org.springframework.boot.SpringApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
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
	
	/**
	 * This method creates a Logger that can be autowired in other classes:{@code
	 *    @Autowired 
	 *    private Logger logger;
	 }*/
	@Bean
	@Scope("prototype")
	public Logger createLogger(InjectionPoint ip) {
	    Class<?> classThatWantsALogger = ip.getField().getDeclaringClass();
	    return LoggerFactory.getLogger(classThatWantsALogger);
	}

}