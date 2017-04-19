/**
 * afterloe - com.github.afterloe/LanchApplication.java
 *
 * Copyright(c) afterloe.
 * MIT Licensed
 *
 * Author:
 *		afterloe <lm6289511@gmail.com> (https://github.com/afterloe)
 *
 * Date:2017年4月19日下午5:03:28 
 */
package com.github.afterloe;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
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

}
