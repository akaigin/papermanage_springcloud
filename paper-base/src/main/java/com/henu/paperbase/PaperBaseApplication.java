package com.henu.paperbase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author tiger
 */
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)
@MapperScan(basePackages = {"com.henu.*.dao"})
@SpringBootApplication
public class PaperBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaperBaseApplication.class, args);
	}
}
