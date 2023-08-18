package com.thejackfolio.microservices.instagramoauthclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InstagramOauthClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstagramOauthClientApplication.class, args);
	}

}
