package com.longpc.mybloggateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MyBlogGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBlogGatewayApplication.class, args);
	}

}
