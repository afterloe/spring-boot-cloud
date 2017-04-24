/**
 * afterloe - com.github.afterloe/LanchApplication.java
 *
 * Copyright(c) afterloe.
 * MIT Licensed
 *
 * Author:
 *		afterloe <lm6289511@gmail.com> (https://github.com/afterloe)
 *
 * Date:2017年4月24日上午10:12:51 
 */
package com.github.afterloe;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.github.afterloe.filter.AccessFilter;

@EnableZuulProxy
@SpringCloudApplication
public class LanchApplication {

	public static void main(String[] args) {
		SpringApplication.run(LanchApplication.class, args);
	}
	
	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter((token) -> !"afterloe".equals(token));
	}

}
