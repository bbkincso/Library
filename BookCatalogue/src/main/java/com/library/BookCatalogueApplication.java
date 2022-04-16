package com.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableWebMvc
//@EnableSwagger2
@EnableDiscoveryClient
public class BookCatalogueApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookCatalogueApplication.class, args);
	}

}
