/**
 * afterloe - com.github.afterloe.service.impl/UserServerImpl.java
 *
 * Copyright(c) afterloe.
 * MIT Licensed
 *
 * Author:
 *		afterloe <lm6289511@gmail.com> (https://github.com/afterloe)
 *
 * Date:2017年4月20日上午10:05:14 
 */
package com.github.afterloe.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.afterloe.domain.User;
import com.github.afterloe.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author afterloe
 *
 */
@Service
public class UserServerImpl implements UserService {
	
	private static  List<User> userList = new ArrayList<User>();
	
	@Value("${mysqldb.datasource.url:afterloe liu}")
	private String autoName;
	@Autowired
	private RestTemplate restTemplate;
	private final String SERVICE_NAME="cloud-webui-userService";

	/* (non-Javadoc)
	 * @see com.github.afterloe.service.UserService#register(java.lang.String, int)
	 */
	@Override
	@HystrixCommand(fallbackMethod="fallbackRegister")
	public User register(String name, int age) {
		return restTemplate.getForObject("http://" + SERVICE_NAME + "/register", User.class);
	}
	
	public User fallbackRegister(String name, int age) {
		System.out.println("HystrixCommand fallbackMethod -- fallbackRegister");
		User _user = new User();
		if (name != null && !name.equals("")) {
			_user.setName(name);
		} else {
			_user.setName(autoName);
		}
		_user.setAge(age);
		userList.add(_user);
		return _user;
	}

	/* (non-Javadoc)
	 * @see com.github.afterloe.service.UserService#getUsers()
	 */
	@SuppressWarnings("unchecked")
	@Override
	@HystrixCommand(fallbackMethod="fallbackGetUsers")
	public Collection<User> getUsers() {
		return restTemplate.getForObject("http://" + SERVICE_NAME + "/getUsers", Collection.class);
	}
	public Collection<User> fallbackGetUsers() {
		System.out.println("HystrixCommand fallbackMethod -- fallbackGetUsers");
		return userList;
	}
}
