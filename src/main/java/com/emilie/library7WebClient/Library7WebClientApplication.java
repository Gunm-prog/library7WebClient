package com.emilie.library7WebClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages="com.emilie")
@EnableFeignClients/*("com.emilie.library7WebClient")*/


public class Library7WebClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(Library7WebClientApplication.class, args);
	}


}
