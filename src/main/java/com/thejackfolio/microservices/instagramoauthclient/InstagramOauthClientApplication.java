package com.thejackfolio.microservices.instagramoauthclient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(
		info = @Info(
				title = "Instagram_Oauth_APIs",
				description = "All the oauth APIs which access Instagram APIs are available here",
				version = "1.0.0",
				termsOfService = "TheJackFolio.com",
				contact = @Contact(
						name = "Jack",
						email = "jk19011999@gmail.com"
				),
				license = @License(
						name = "TheJackFolio",
						url = "TheJackFolio.com"
				)
		)
)
public class InstagramOauthClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstagramOauthClientApplication.class, args);
	}

}
