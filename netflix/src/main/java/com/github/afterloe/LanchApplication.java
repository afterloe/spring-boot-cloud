/**
 * afterloe - com.github.afterloe/LanchApplication.java
 *
 * Copyright(c) afterloe.
 * MIT Licensed
 *
 * Author:
 *		afterloe <lm6289511@gmail.com> (https://github.com/afterloe)
 *
 * Date:2017年4月18日上午11:52:19 
 */
package com.github.afterloe;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configurable
@EnableAutoConfiguration
@EnableEurekaClient
@RestController
public class LanchApplication {

	@RequestMapping("/")
	public String home(){
		return "Hello afterloe";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(LanchApplication.class, args);
	}
}