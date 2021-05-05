package com.emilie.library7WebClient;

import Exceptions.FeignErrorDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients/*("com.emilie.library7WebClient")*/
@EnableEurekaClient

public class Library7WebClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(Library7WebClientApplication.class, args);
	}

	@Bean
	public FeignErrorDecoder errorDecoder() {
		return new FeignErrorDecoder();
	}
}
