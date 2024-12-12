package com.stephenowinosewstyle.Sew_Style_Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan( basePackages = {"com.stephenowinosewstyle.Sew_Style_Backend.entity"} )
public class SewStyleBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SewStyleBackendApplication.class, args);
	}

}
