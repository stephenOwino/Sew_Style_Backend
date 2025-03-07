package com.stephenowinosewstyle.Sew_Style_Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication(scanBasePackages = "com.stephenowinosewstyle.Sew_Style_Backend")
public class SewStyleBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SewStyleBackendApplication.class, args);
	}

}

