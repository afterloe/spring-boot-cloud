/**
 * afterloe - com.github.afterloe/LanchApplication.java
 *
 * Copyright(c) afterloe.
 * MIT Licensed
 *
 * Author:
 *		afterloe <lm6289511@gmail.com> (https://github.com/afterloe)
 *
 * Date:2017年4月20日上午9:54:30 
 */
package com.github.afterloe;

import org.jboss.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class LanchApplication {

	public static void main(String[] args) {
		SpringApplication.run(LanchApplication.class, args);
	}
	
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public Logger.Level feignLogger() {
		return Logger.Level.INFO;
	}
}
