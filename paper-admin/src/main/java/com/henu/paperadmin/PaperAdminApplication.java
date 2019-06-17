package com.henu.paperadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients(basePackages = {"com.henu"})
@EnableCaching
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.henu.paperadmin", "com.henu.papercommon"})
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class})
public class PaperAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaperAdminApplication.class, args);
    }

}
