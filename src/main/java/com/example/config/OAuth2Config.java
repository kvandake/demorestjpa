package com.example.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

/**
 * Created by home on 23.02.16.
 */
@Configuration
public class OAuth2Config {


    @Autowired
    private DataSource dataSource;

    @Bean
    @Primary
    protected JwtAccessTokenConverter jwtAccessTokenConverter() {
        return new JwtAccessTokenConverter();
    }


    @Bean
    @Primary
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    @Primary
    public ClientDetailsService clientDetailsService() {return new JdbcClientDetailsService(dataSource);}

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() throws Exception {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetailsService());
        tokenServices.setAccessTokenValiditySeconds(0);
        tokenServices.setTokenStore(tokenStore());
        return tokenServices;
    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends
            AuthorizationServerConfigurerAdapter {


        @Autowired
        @Qualifier("authenticationManager")
        private AuthenticationManager authenticationManager;

        @Autowired
        @Qualifier("clientDetailsService")
        private ClientDetailsService clientDetailsService;


        @Autowired
        @Qualifier("tokenServices")
        private AuthorizationServerTokenServices tokenService;


        @Autowired
        private JwtAccessTokenConverter jwtAccessTokenConverter;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
                throws Exception {
            endpoints
                    .authenticationManager(authenticationManager)
                    .accessTokenConverter(jwtAccessTokenConverter)
                    .tokenServices(tokenService);
        }


        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.withClientDetails(clientDetailsService);
        }
    }

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Autowired
        private DataSource dataSource;

        @Autowired
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.tokenStore(new JdbcTokenStore(dataSource));
            resources.authenticationManager(authenticationManager);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .csrf()
                    .disable()
                    .authorizeRequests()
                    .antMatchers("/files/**").permitAll()
                    .anyRequest()
                    .permitAll();
        }

    }

}
