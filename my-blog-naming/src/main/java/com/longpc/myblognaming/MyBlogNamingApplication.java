package com.longpc.myblognaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MyBlogNamingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBlogNamingApplication.class, args);
	}

}
