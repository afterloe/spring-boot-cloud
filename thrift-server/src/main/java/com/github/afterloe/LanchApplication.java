/**
 * afterloe - com.github.afterloe/LanchApplication.java
 *
 * Copyright(c) afterloe.
 * MIT Licensed
 *
 * Author:
 *		afterloe <lm6289511@gmail.com> (https://github.com/afterloe)
 *
 * Date:2017年4月21日下午12:00:49 
 */
package com.github.afterloe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class LanchApplication {

	public static void main(String[] args) {
		SpringApplication.run(LanchApplication.class, args);
	}
}
