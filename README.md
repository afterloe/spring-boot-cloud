### SOA 框架选型对比


#### DUBBO
国内SOA框架为DUBBO（http://dubbo.io）, DUBBO是一个分布式服务框架，致力于提供高性能和透明化的RPC远程服务调用方案，是阿里巴巴SOA服务化治理方案的核心框架。

#### Spring Cloud
Spring cloud (http://projects.spring.io/spring-cloud) , 给开发者提供快速的构建工具，通体采用模块话设计，使得开发人员能够快速的构建开发出一个完整的分布式系统包括配置管理，服务发现，断路器，智能路由，微代理，控制总线等等。

## 易用性

### DUBBO
dubbo 使用 注解的方式进行编码
```java
package com.github.afterloe.spring_boot_dubbo.provider.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.afterloe.dubbo.PersonService;
import com.github.afterloe.spring_boot_dubbo.provider.domain.Person;

@Service(version = "0.0.1")
public class PersonServiceImpl implements PersonService {

	@Override
	public Person registry(String name, char sex, int age) {
		return new Person(name, age, sex);
	}

	@Override
	public Person findByName(String name) {
		System.out.printf("search for %s \r\n", name);
		return new Person();
	}

}
```

再服务的实现上采用Service注解即可将一个server变成一个服务，不过该服务是注册在Zookeeper上，提供的一个类似rpc的服务

### Spring Cloud
Spring cloud也是采用注解的方式进行编码
```java
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
```
在实现上配合注解EnableDiscoveryClient就可自动被发现，不过这些都需要一个服务端和客户端

## 区别

### DUBBO
Dubbo更像是一个rpc调度框架，而不是一个平台框架，它的方向是再服务的远程调度，再实际开发的时候，给外部直接调度内部接口是存在一定风险的，在权限上Dubbo可以实现一个权限控制，再日志和查询上，rpc的效率要更加高效一点。多语言方面，Dubbo是基于thrift进行扩展，多语言方面不用过多的担心。

### Spring Cloud
Spring Cloud是一个完整的生态圈，使用Spring Cloud的生态圈可以快速的部署一个分布式开发平台，利用该平台，可以快速的部署服务和查询，使用Spring 现有的生态圈，减少开发压力。而部署的服务没有任何限制，无论是RPC服务还是web服务，还是数据服务都不用太多担心，而且暴漏的服务可以不适用java语言开发，在摸索的阶段笔者采用了node.js一样可以可以注册服务，监听心跳，为什么呢？因为Spring Cloud采用的是http形式的restful API进行调度的(https://github.com/Netflix/eureka/wiki/Eureka-REST-operations) 所以，只有任何语言实现这个就能将服务发布到我们平台之上。

## 结论
在大平台上也就是我们的公用SASS平台上可以采用Spring Cloud作为底层技术，进行应用&服务开发，在RPC调度一块可以使用DUBBO或者自定义的Thrift + Zookeeper进行RPC服务的注册，这样可以保证更多的语言接入和开发，在使用方面，Spring Cloud的资料会比DUBBO的丰富，而去Spring Cloud的应用是类似一个jar包形式存在的，配合Docker提供运行环境可以实现多服务并存，在配合Spring 自己提供的智能路由就能搞定负载均衡，所以推荐SASS平台使用SPring Cloud，SOA上使用DUBBO。两个强强联手即可打造出我们自己的PaaS开发云平台。
