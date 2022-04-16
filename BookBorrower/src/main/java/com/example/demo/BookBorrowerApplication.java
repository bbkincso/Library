package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@EnableResourceServer
public class BookBorrowerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookBorrowerApplication.class, args);
	}

}
