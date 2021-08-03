package com.yadlings.restfull.Security.Configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@EnableResourceServer
@Configuration
public class ResourceServerConf extends ResourceServerConfigurerAdapter {
    public void configure(ResourceServerSecurityConfigurer configurer){
        configurer.resourceId("Quick_res");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("/oauth2/v3/poll/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth2/v3/poll/**")
                .authenticated();
    }
}
