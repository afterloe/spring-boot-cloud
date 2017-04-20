/**
 * afterloe - com.github.afterloe.collection/UserCollection.java
 *
 * Copyright(c) afterloe.
 * MIT Licensed
 *
 * Author:
 *		afterloe <lm6289511@gmail.com> (https://github.com/afterloe)
 *
 * Date:2017年4月20日上午10:39:08 
 */
package com.github.afterloe.collection;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.afterloe.domain.User;
import com.github.afterloe.service.UserService;

@RestController
public class UserCollection {

	@Autowired
	private UserService userService;

	@RequestMapping("/getUsers")
	public ResponseEntity<Collection<User>> users() {
		Collection<User> users = userService.getUsers();
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
	}

	@RequestMapping("/register")
	public ResponseEntity<User> register(@RequestParam(value = "name") String name,
			@RequestParam(value = "age", defaultValue = "10") int age) {
		User _user = userService.register(name, age);
		return new ResponseEntity<User>(_user, HttpStatus.OK);
	}
}
