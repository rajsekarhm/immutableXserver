package com.immutable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.immutable.request.accounts","com.dependencies.jedis"})
public class ImmutableApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ImmutableApplication.class, args);
	}

}
