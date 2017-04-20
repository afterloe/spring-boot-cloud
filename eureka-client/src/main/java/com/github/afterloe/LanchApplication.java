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

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.afterloe.domain.User;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class LanchApplication {
	
	@Value("${mysqldb.datasource.url:afterloe test}")
	protected String name;
	private Collection<User> userList = new ArrayList<User>();
	
	@RequestMapping("/")
	public String home() {
		return "Hello " + name ;
	}
	
	@RequestMapping("/getUsers")
	public ResponseEntity<Collection<User>> users() {
		return new ResponseEntity<Collection<User>>(userList, HttpStatus.OK);
	}

	@RequestMapping("/register")
	public ResponseEntity<User> register(@RequestParam(value = "name", defaultValue="proje") String name,
			@RequestParam(value = "age", defaultValue = "10") int age) {
		User _user = new User();
		_user.setAge(age);
		_user.setName(name);
		userList.add(_user);
		return new ResponseEntity<User>(_user, HttpStatus.OK);
	}

	public static void main(String[] args) {
		SpringApplication.run(LanchApplication.class, args);
	}

}
