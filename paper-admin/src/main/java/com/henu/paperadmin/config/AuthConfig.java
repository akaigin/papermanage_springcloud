package com.henu.paperadmin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AuthConfig extends WebMvcConfigurerAdapter {
//    @Bean
//    public AuthIntercepter authIntercepter() {
//        return new AuthIntercepter();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration addInterceptor = registry.addInterceptor(authIntercepter());
//
//        // 排除配置
//        addInterceptor.excludePathPatterns("/error");
//        addInterceptor.excludePathPatterns("/login**");
//        addInterceptor.excludePathPatterns("/test**");
//
//        // 拦截配置
//        addInterceptor.addPathPatterns("/**");
//    }
}
