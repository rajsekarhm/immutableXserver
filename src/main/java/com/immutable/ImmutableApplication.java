package com.immutable;


import com.dependencies.jedis.IJedis;
import com.dependencies.jedis.JedisImx;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class ImmutableApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ImmutableApplication.class, args);
		IJedis redis = new JedisImx();
		JedisImx.makeConnection();
	}

}
