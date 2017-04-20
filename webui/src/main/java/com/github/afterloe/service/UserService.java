/**
 * afterloe - com.github.afterloe.service/UserService.java
 *
 * Copyright(c) afterloe.
 * MIT Licensed
 *
 * Author:
 *		afterloe <lm6289511@gmail.com> (https://github.com/afterloe)
 *
 * Date:2017年4月20日上午10:00:43 
 */
package com.github.afterloe.service;

import java.util.Collection;

import com.github.afterloe.domain.User;

public interface UserService {

	User register(String name, int age);
	Collection<User> getUsers();
}
