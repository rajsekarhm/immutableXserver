package com.immutable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication (exclude = {ErrorMvcAutoConfiguration.class})
@ComponentScan(basePackages = {"com.immutable.request","com.dependencies.jedis"})
public class ImmutableApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ImmutableApplication.class, args);
	}

}
