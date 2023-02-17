package com.harvex.HarvexSecurity.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //there is configure auth
    protected void configure (AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(); //TODO
    }
}
