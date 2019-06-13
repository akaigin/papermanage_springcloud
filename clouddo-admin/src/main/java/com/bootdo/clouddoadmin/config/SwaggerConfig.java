package com.bootdo.clouddoadmin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Arrays;
import java.util.Collections;
//把import写进来主要是为了确保万无一失

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //这个东西是项目的根路径，也就是“/oauth/token”前面的那一串
    //这个东西在配置文件里写的，大家也可以直接写死在配置文件中
    private String AUTH_SERVER="http://localhost:8005";

    /**
     * 主要是这个方法，其他的方法是抽出去的，所以大家不要害怕为啥有这么多方法
     * 在 basePackage 里面写需要生成文档的 controller 路径
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bootdo.clouddoadmin.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Collections.singletonList(securityScheme()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    /**
     * 这个方法主要是写一些文档的描述
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "论文管理系统API接口文档",
                "谨为更好的开发!",
                "1.0",
                "",
                new Contact("tiger", "", "1151916951@qq.com"),
                "", "", Collections.emptyList());
    }

    /**
     * 这个类决定了你使用哪种认证方式，我这里使用密码模式
     * 其他方式自己摸索一下，完全莫问题啊
     */
    private SecurityScheme securityScheme() {
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(AUTH_SERVER + "/oauth/token");

        return new OAuthBuilder()
                .name("spring_oauth")
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes()))
                .build();
    }

    /**
     * 这里设置 swagger2 认证的安全上下文
     */
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference("spring_oauth", scopes())))
                .forPaths(PathSelectors.any())
                .build();
    }

    /**
     * 这里是写允许认证的scope
     */
    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{
                new AuthorizationScope("all", "All scope is trusted!")
        };
    }

}