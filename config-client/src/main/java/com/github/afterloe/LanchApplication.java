/**
 * afterloe - com.github.afterloe/LanchApplication.java
 *
 * Copyright(c) afterloe.
 * MIT Licensed
 *
 * Author:
 *		afterloe <lm6289511@gmail.com> (https://github.com/afterloe)
 *
 * Date:2017年4月19日上午9:41:12 
 */
package com.github.afterloe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableAutoConfiguration
@RestController
public class LanchApplication {
	
	@Value("${mysqldb.datasource.url:afterloe test}")
	protected String name;
	
	@RequestMapping("/")
	public String home() {
		return "Hello " + name ;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(LanchApplication.class, args);
	}
	
	@Autowired
	protected void setEnviroment(Environment env) {
		System.out.println(env);
		System.out.println(env.getProperty("springCloudClientHostInfo"));
	}
}