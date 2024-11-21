package com.bankaio.Bankaio;

import config.ModelMapperConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ModelMapperConfig.class)
public class BankaioApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankaioApplication.class, args);
	}

}
