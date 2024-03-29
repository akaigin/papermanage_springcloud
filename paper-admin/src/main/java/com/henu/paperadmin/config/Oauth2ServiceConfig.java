package com.henu.paperadmin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @author tiger
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2ServiceConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    DataSource dataSource;

    @Autowired
    UserDetailsService userDetail;

    @Autowired
    private WebResponseExceptionTranslator webResponseExceptionTranslator;
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("app") // clientId, 可以类比为用户名
                .scopes("app")// 授权范围
                .authorizedGrantTypes("password","authorization_code", "refresh_token")// 授权类型，这里选择授权码
                .secret(new BCryptPasswordEncoder().encode("123456"));// secret， 可以类比为密码
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                    .authenticationManager(authenticationManager)
                    .tokenStore(jdbcTokenStore())
                    .exceptionTranslator(webResponseExceptionTranslator);

    }

    JdbcTokenStore jdbcTokenStore(){
        return new JdbcTokenStore(dataSource);
    }

//    @Bean
//    RedisTokenStore redisTokenStore(){
//        return new RedisTokenStore(connectionFactory);
//    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }


}
